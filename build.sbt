name := "play-liquibase"

organization := "play"

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
    "play" % "play-jdbc_2.10" % "2.1.2",
    "org.liquibase" % "liquibase-core" % "2.0.3"
)

EclipseKeys.withSource := true
