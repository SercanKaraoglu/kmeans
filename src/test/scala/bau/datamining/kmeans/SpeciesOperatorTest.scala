package bau.datamining.kmeans

import bau.datamining.kmeans.io.Helpers
import org.scalatest.WordSpec

/**
  * Created by sercan on 28.12.2016.
  */
class SpeciesOperatorTest extends WordSpec {

  import Helpers._
  import bau.datamining.kmeans.io.Specy

  val a = new Specy(1, 5.1, 3.5, 1.4, 0.2, "Iris-setosa")
  println(a)
  val b = new Specy(2, 4.9, 3.0, 1.4, 0.2, "Iris-setosa")
  println(b)

  s"When species $a and $b" when {
    val c = Array(a, b).sum
    " add" should {
      " , it should add sepal lenght, sepal width, petal length, petal width" in {
        assert(10d == c.sepalLengthCm, "Sepal Length calculated right")
        assert(6.5 == c.sepalWidthCm, "Sepal Width calculated right")
        assert(2.8 == c.petalLengthCm, "Sepal Length calculated right")
        assert(0.4 == c.petalWidthCm, "Sepal Length calculated right")
      }
    }
  }

  s"When sum of $a and $b divided by 2" when {
    val c = Array(a, b).sum
    " it should divide its sepal lenght, sepal width, petal length, petal width" should {
      val d = c / 2
      " as 5, 3.25, 1.4, 0.2 respectively " in {
        assert(5d == d.sepalLengthCm, "Sepal Length calculated right")
        assert(3.25 == d.sepalWidthCm, "Sepal Width calculated right")
        assert(1.4 == d.petalLengthCm, "Sepal Length calculated right")
        assert(0.2 == d.petalWidthCm, "Sepal Length calculated right")
      }
    }
  }

  s"When $a and $b is given " when {
    val means = Array(a, b)
    val c = new Specy(3, 5.0, 3.4, 1.3, 0.1, "Iris-setosa")
    s" and cluster center is $c" should {
      val d = means.minBy(_.distance(c))
      s" then closest point to cluster center is $a" in {
        assert(d==a)
      }
    }
  }

}