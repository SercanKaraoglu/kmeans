package bau.datamining.kmeans

import bau.datamining.kmeans.io._

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.collection.parallel.immutable.ParVector
import scala.collection.parallel.mutable.ParArray
import scala.util.Random

class KMeans(val seed: Int) {
  val rand = new Random(seed)
  println(s"****KMeans with random seed $seed ****")
  val emptySpecies: ParArray[Specy] = ParArray()

  private def initMeans(k: Int, species: Vector[Specy]): ArrayBuffer[Specy] = (0 until k).map(_ => species(rand.nextInt(species.length))).to[ArrayBuffer]

  import Helpers._

  @tailrec
  private def kmeans(species: ParVector[Specy], means: ParArray[Specy], eta: Double): ParArray[(Specy, ParVector[Specy])] = {
    val newMeans: ParArray[(Specy, ParVector[Specy])] =
      means.map(mean => {
        species.groupBy(p => means.minBy(_.distance(p))).get(mean) match {
          case Some(c) => (c.sum / c.length, c)
          case None => (mean, ParVector(mean))
        }
      })
    val converged = (means zip newMeans.map(_._1)).forall {
      case (oldMean, newMean) => oldMean.distance(newMean) <= eta
    }
    if (!converged) kmeans(species, newMeans.map(_._1), eta) else newMeans
  }
}

object KMeans {
  def main(args: Array[String]): Unit = {
    val k: Int = 3
    val eta: Double = 1E-5
    val numOfPoints: Int = 150

    val (lines, metaData) = ReadToVector.fromFile("data/iris.csv", ",", Array(NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, CATEGORICAL))
    val species = lines.map(line => new Specy(line.getInt(0), line.getDouble(1), line.getDouble(2), line.getDouble(3), line.getDouble(4), line.getString(5)))
    val parPoints = species.par

    //After you implement the algorithm run it with the iris dataset. Set K=3 and run k-means algorithms 5 times with different random initial centroids.
    val seeds = Array(1970, 1980, 1990, 2000, 2010)
     seeds.map(KMeans(_))
          .map { kmeans =>
              val means = kmeans.initMeans(k, species)
              val parMeans = means.par
              kmeans.kmeans(parPoints, parMeans, eta)
          }.zipWithIndex
           .foreach { case (results: ParArray[(Specy, ParVector[Specy])],index: Int) =>
              //After the algorithm finished calculate and report the number of points in each cluster after each run
              println(s"Run #$index")
                results.toSeq.zipWithIndex
                        .foreach{case (clusters: (Specy, ParVector[Specy]), clusterNumber: Int)=>
                            val mean = clusters._1
                            val size = clusters._2.size
                            //. In the plot make the points belonging to the same cluster have the same color and points belonging to different clusters have different colors.
                            // What do you think about the clusters produced by k-means? Are they the "natural" clusters?
                            println(s"cluster #$clusterNumber has size of $size elements and has mean of $mean ")
                          clusters._2.groupBy(_.specy)
                                     .foreach{case (specy,elements)=>
                                        val specyCountInCluster = elements.size
                                        println(s"cluster #$clusterNumber contains $specyCountInCluster of $specy")
                                     }
                          }

                println(s"*************************End of Run #$index*************************")
           }
  }

  def apply(seed: Int): KMeans = new KMeans(seed)
}