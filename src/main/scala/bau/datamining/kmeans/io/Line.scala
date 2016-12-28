package bau.datamining.kmeans.io

/**
  * Created by sercan on 3.12.2016.
  */
class Line(private val row: Array[String]) extends Row {

  override def getDouble(i: Int) = row(i).toDouble

  override def getString(i: Int): String = row(i)

  override def indices(): Range = row.indices

  override def getInt(i: Int): Int = row(i).toInt
}
