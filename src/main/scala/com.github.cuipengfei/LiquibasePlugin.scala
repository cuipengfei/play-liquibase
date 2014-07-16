package com.github.cuipengfei

import play.api._
import liquibase.changelog.ChangeSet
import play.api.db.{DB, DBPlugin}
import liquibase.resource.FileSystemResourceAccessor
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.logging.LogLevel
import scala.collection.JavaConversions._

/**
 * @date: 03.04.12
 * @author: Kaa
 */

class LiquibasePlugin(app: Application) extends Plugin {
  val TestContext = "test"
  val DeveloperContext = "dev"
  val ProductionContext = "prod"
  val noDataBasePluginMsg = "there should be a database plugin registered at this point" +
    " but looks like it's not available, so liquibase won't work." +
    " Please make sure you register a db plugin properly"

  private def getScriptDescriptions(changeSets: Seq[ChangeSet]) = {
    changeSets.zipWithIndex.map {
      case (changeSet, idx) =>
        "" + idx + ". " + changeSet.getId +
          Option(changeSet.getDescription).fold("")(" (" + _ + ")") +
          " by " + changeSet.getAuthor
    }.mkString("\n")
  }

  override def onStart() {
    val api = app.plugin[DBPlugin].map(_.api).getOrElse(throw new Exception(noDataBasePluginMsg))

    api.datasources.foreach {
      case (dataSource, dbName) => {
        val fileOpener = new FileSystemResourceAccessor(app.path.getAbsolutePath)
        DB.withConnection(dbName)(connection => {
          val liqui = new Liquibase("conf/liquibase/" + dbName + "/modules.xml", fileOpener, new JdbcConnection(connection))
          liqui.getLog.setLogLevel(LogLevel.WARNING)

          app.mode match {
            case Mode.Test => liqui.update(TestContext)
            case Mode.Dev if isLiquibaseConfigDefined(dbName) => liqui.update(DeveloperContext)
            case Mode.Prod if isLiquibaseConfigDefined(dbName) => liqui.update(ProductionContext)
            case Mode.Prod =>
              Logger("play").warn("Your production database [" + dbName + "] needs Liquibase updates! \n\n" + prodDesc(liqui))
              Logger("play").warn("Run with -DapplyLiquibase." + dbName + "=true if you want to run them automatically (be careful)")

              throw liquibasePluginNotAppliedError(dbName, liqui)
            case _ => liquibasePluginNotAppliedError(dbName, liqui)
          }
        })(app)
      }
    }
  }

  private def prodDesc(liqui: Liquibase): String = {
    getScriptDescriptions(liqui.listUnrunChangeSets(ProductionContext))
  }

  private def liquibasePluginNotAppliedError(dbName: String, liqui: Liquibase): PlayException = {
    new PlayException("Liquibase script should be applyed, set applyLiquibase." +
      dbName + "=true in application.conf",
      prodDesc(liqui))
  }

  private def isLiquibaseConfigDefined(dbName: String): Boolean =
    app.configuration.getBoolean("applyLiquibase." + dbName).getOrElse(false)

  override lazy val enabled = {
    app.configuration.getConfig("db").isDefined && {
      !app.configuration.getString("liquibaseplugin").filter(_ == "disabled").isDefined
    }
  }
}
