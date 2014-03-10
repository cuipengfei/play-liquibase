resolvers ++= Seq(
        Classpaths.typesafeResolver
)

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.2.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "0.2.1")