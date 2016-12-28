package bau.datamining.kmeans.io

/**
  * Created by sercan on 2.12.2016.
  */
object Score {
  val entropy: (Double, Double) => Double = (c0, c1) => {
    if (c0 == 0 || c1 == 0) {
      0
    } else {
      val total = c0 + c1
      val f0 = c0 / total
      val f1 = c1 / total
      -(f0 * log2(f0)) - (f1 * log2(f1))
    }
  }


  private[kmeans] def log2(x: Double) = scala.math.log(x) / scala.math.log(2)
}
