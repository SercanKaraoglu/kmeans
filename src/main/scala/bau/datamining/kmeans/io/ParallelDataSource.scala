package bau.datamining.kmeans.io

import java.io.File

import bau.datamining.kmeans.DataSet

import scala.io.Source

/**
  * Created by sercan on 3.12.2016.
  */
case class TemplateMetaData(types: Array[String], names: Array[String])

case class ParallelDataSource(source: DataSet, metaData: TemplateMetaData)

object ParallelDataSource {
  def apply(path: String,seperator: String, types: Array[String]): ParallelDataSource = {
    val r = ReadToVector.fromFile(path,seperator, types)
    new ParallelDataSource(r._1.par, r._2)
  }

  def apply(matrix: Array[Array[String]], types: Array[String], names: Array[String]): ParallelDataSource = {
    new ParallelDataSource(ReadToVector.fromMemory(matrix, types, names).par, TemplateMetaData(types, names))
  }
}


object ReadToVector {
  def fromFile(path: String, seperator: String, types: Array[String]): (Vector[Line], TemplateMetaData) = {
    val lines = Source.fromFile(new File(path)).getLines()
    val headerText = lines.take(1).next()
    val header = headerText.split(seperator).map(_.replace("\"", ""))
    val map: Iterator[Line] = lines.map(it => it.split(seperator).map(_.replace("\"", ""))).map(new Line(_))
    (lines.map(it => it.split(seperator).map(_.replace("\"", ""))).map(new Line(_)).toVector, TemplateMetaData(types, header))
  }

  def fromMemory(matrix: Array[Array[String]], types: Array[String], names: Array[String]): Vector[Line] = {
    matrix.map(new Line(_)).toVector
  }
}

