name := "play-liquibase"

organization := "com.github.cuipengfei"

version := "1.1-SNAPSHOT"

scalaVersion := "2.11.1"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-jdbc" % "2.3.1",
  "org.liquibase" % "liquibase-core" % "3.2.0"
)

pomExtra := <url>https://github.com/cuipengfei/play-liquibase</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:cuipengfei/play-liquibase.git</url>
    <connection>scm:git:git@github.com:cuipengfei/play-liquibase.git</connection>
  </scm>
  <developers>
    <developer>
      <id>cuipengfei</id>
      <name>Cui Pengfei</name>
      <url>http://cuipengfei.me</url>
    </developer>
  </developers>