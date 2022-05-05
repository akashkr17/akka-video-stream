package edu.knoldus

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

object Runner extends App {

//  import org.mongodb.scala._
//  // ...
//  // Replace the uri string with your MongoDB deployment's connection string.
//  val uri: String = "mongodb+srv://akashkr17:akashkr@cluster0.dicy2.mongodb.net/test"
//  System.setProperty("org.mongodb.async.type", "netty")
//  val client: MongoClient = MongoClient(uri)
//  val db: MongoDatabase = client.getDatabase("stream-app")
//  println(db.createCollection(
//    "testcollection"
//  ))

  val host = "localhost"
  val port = 8090
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty,"video-stream-system")
  Http().newServerAt(host,port).bind(Streamer.route)
}
