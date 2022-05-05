name := "video-stream"

version := "0.1"

scalaVersion := "2.13.8"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.14",
  "com.typesafe.akka" %% "akka-stream" % "2.6.14",
  "com.typesafe.akka" %% "akka-http" % "10.2.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.6.14"
)
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.7"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.2.3"
