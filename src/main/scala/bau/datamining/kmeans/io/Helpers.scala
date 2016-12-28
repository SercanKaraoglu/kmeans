package bau.datamining.kmeans.io

import scala.math.Ordering

/**
  * Created by sercan on 28.12.2016.
  */
object Helpers {
  val zero: Specy = new Specy(-1,0,0,0,0,"0")

  trait NumericSpecy extends Numeric[bau.datamining.kmeans.io.Specy] with Ordering[bau.datamining.kmeans.io.Specy] {
    override def zero: Specy = Helpers.zero

    override def plus(x: Specy, y: Specy): Specy = new Specy(Int.MinValue,
      x.sepalLengthCm + y.sepalLengthCm,
      x.sepalWidthCm + y.sepalWidthCm,
      x.petalLengthCm + y.petalLengthCm,
      x.petalWidthCm + y.petalWidthCm,
      x.specy + "-" + y.specy)

    override def minus(x: Specy, y: Specy): Specy = new Specy(Int.MinValue,
      x.sepalLengthCm - y.sepalLengthCm,
      x.sepalWidthCm - y.sepalWidthCm,
      x.petalLengthCm - y.petalLengthCm,
      x.petalWidthCm - y.petalWidthCm,
      x.specy + "-" + y.specy)

    override def times(x: Specy, y: Specy): Specy = new Specy(Int.MinValue,
      x.sepalLengthCm * y.sepalLengthCm,
      x.sepalWidthCm * y.sepalWidthCm,
      x.petalLengthCm * y.petalLengthCm,
      x.petalWidthCm * y.petalWidthCm,
      x.specy + "-" + y.specy)

    override def negate(x: Specy): Specy = new Specy(Int.MinValue,
      -x.sepalLengthCm,
      -x.sepalWidthCm,
      -x.petalLengthCm,
      -x.petalWidthCm,
      "-" + x.specy)

    override def fromInt(x: Int): Specy = throw new UnsupportedOperationException

    override def toInt(x: Specy): Int = throw new UnsupportedOperationException

    override def toLong(x: Specy): Long = throw new UnsupportedOperationException

    override def toFloat(x: Specy): Float = throw new UnsupportedOperationException

    override def toDouble(x: Specy): Double = throw new UnsupportedOperationException

    override def compare(x: Specy, y: Specy): Int = x.distanceToOrigin.compare(y.distanceToOrigin)

  }
  implicit object SpecyIsIntegral extends NumericSpecy
}
