package edu.knoldus

import java.io.File

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes, StatusCodes}
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString

import scala.concurrent.Future


object Streamer extends Directives {
    val route: Route =
      path("api" / "files" / Segment) { fileName =>
        get {
          optionalHeaderValueByName("Range") {
            case None => complete(StatusCodes.RangeNotSatisfiable)
            case Some(range) => complete(stream(range,fileName))
          }
        }
      }

  private def stream(rangeHeader: String,fileName: String): HttpResponse = {
    val path = s"/home/knoldus/AkashKumar/FSMTodo/video-stream/src/main/resources/$fileName"
println(path)
    val file = new File(path)
    val fileSize = file.length()

    val range = rangeHeader.split("=")(1).split("-")
    val start = range(0).toInt
    val end = fileSize - 1

    val headers = List(
      RawHeader("Content-Range", s"bytes ${start}-${end}/${fileSize}"),
      RawHeader("Accept-Ranges", s"bytes")
    )

    val fileSource: Source[ByteString, Future[IOResult]] = FileIO.fromPath(file.toPath, 1024, start)
    val responseEntity = HttpEntity(MediaTypes.`video/mp4`, fileSource)
    HttpResponse(StatusCodes.PartialContent, headers, responseEntity)
  }
}