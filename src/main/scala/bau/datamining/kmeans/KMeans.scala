package bau.datamining.kmeans

import bau.datamining.kmeans.io._

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.collection.parallel.immutable.ParVector
import scala.collection.parallel.mutable.ParArray
import scala.util.Random

class KMeans(val seed: Int, val k: Int, val eta: Double, val species: Vector[Specy]) {
  val rand = new Random(seed)
  val _species = species.par
  val means: ParArray[Specy] = (0 until k).map(_ => species(rand.nextInt(species.length))).to[ArrayBuffer].par

  import Helpers._

  private def kmeans(): ParArray[(Specy, ParVector[Specy])] = {
    @tailrec
    def _kmeans(means: ParArray[Specy]): ParArray[(Specy, ParVector[Specy])] = {
      val newMeans: ParArray[(Specy, ParVector[Specy])] =
        means.map(mean => {
          _species.groupBy(p => means.minBy(_.distance(p))).get(mean) match {
            case Some(c) => (c.sum / c.length, c)
            case None => (mean, ParVector(mean))
          }
        })
      val converged = (means zip newMeans.map(_._1)).forall {
        case (oldMean, newMean) => oldMean.distance(newMean) <= eta
      }
      if (!converged) _kmeans(newMeans.map(_._1)) else newMeans
    }

    _kmeans(means)
  }
}

object KMeans {
  def main(args: Array[String]): Unit = {
    val k: Int = 3
    val eta: Double = 1E-5
    val numOfPoints: Int = 150

    val (lines, metaData) = ReadToVector.fromFile("data/iris.csv", ",", Array(NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, CATEGORICAL))
    val species = lines.map(line => new Specy(line.getInt(0), line.getDouble(1), line.getDouble(2), line.getDouble(3), line.getDouble(4), line.getString(5)))

    //After you implement the algorithm run it with the iris dataset. Set K=3 and run k-means algorithms 5 times with different random initial centroids.
    val seeds = Array(1970, 1980, 1990, 2000, 2010)
     seeds.map(KMeans(_, k, eta, species).kmeans()).zipWithIndex
          .foreach {
            DisplayResults
          }
  }

  def apply(seed: Int, k: Int, eta: Double, species: Vector[Specy]): KMeans = new KMeans(seed, k, eta, species)
}