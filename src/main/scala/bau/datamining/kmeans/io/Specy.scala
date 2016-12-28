package bau.datamining.kmeans.io

/**
  * Created by sercan on 27.12.2016.
  */

class Specy(var id: Int, val sepalLengthCm: Double, val sepalWidthCm: Double, val petalLengthCm: Double, val petalWidthCm: Double, val specy: String) {

  import math._

  def distanceToOrigin = {
    sqrt(pow(sepalLengthCm - 0, 2) +
      pow(sepalWidthCm - 0, 2) +
      pow(petalLengthCm - 0, 2) +
      pow(petalWidthCm - 0, 2))
  }

  def distance(y: Specy) = {
    sqrt(pow(y.sepalLengthCm - sepalLengthCm, 2) +
      pow(y.sepalWidthCm - sepalWidthCm, 2) +
      pow(y.petalLengthCm - petalLengthCm, 2) +
      pow(y.petalWidthCm - petalWidthCm, 2))
  }

  def / (x: Int): Specy = new Specy(Int.MinValue, sepalLengthCm / x, sepalWidthCm / x, petalLengthCm / x, petalWidthCm / x, s"$id-$specy/x")

  override def toString: String = s"Specy[sepal length cm: $sepalLengthCm, sepal width cm $sepalWidthCm, petal length cm $petalLengthCm, petal width cm $petalWidthCm]"
}