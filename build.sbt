name := "play-liquibase"

organization := "com.code-troopers.play"

version := "1.1-SNAPSHOT"

scalaVersion := "2.10.4"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-java-jdbc" % "2.2.1",
    "org.liquibase" % "liquibase-core" % "3.1.1"
)

pomExtra := (
  <url>https://github.com/CedricGatay/play-liquibase</url>
    <licenses>
      <license>
        <name>BSD-style</name>
        <url>http://www.opensource.org/licenses/bsd-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:CedricGatay/play-liquibase.git</url>
      <connection>scm:git:git@github.com:CedricGatay/play-liquibase.git</connection>
    </scm>
    <developers>
      <developer>
        <id>cgatay</id>
        <name>Cedric Gatay</name>
        <url>http://www.code-troopers.com</url>
      </developer>
    </developers>
  )