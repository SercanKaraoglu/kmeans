package bau.datamining.kmeans

import bau.datamining.kmeans.io.Specy

import scala.collection.parallel.immutable.ParVector
import scala.collection.parallel.mutable.ParArray

/**
  * Created by sercan on 28.12.2016.
  */
object DisplayResults extends (((ParArray[(Specy, ParVector[Specy])], Int)) =>Unit) {
  override def apply(v1: (ParArray[(Specy, ParVector[Specy])], Int)): Unit = {
    val results = v1._1
    val index = v1._2
    //After the algorithm finished calculate and report the number of points in each cluster after each run
    println(s"Run #$index")
    results.toSeq.zipWithIndex
      .foreach { case (clusters: (Specy, ParVector[Specy]), clusterNumber: Int) =>
        val mean = clusters._1
        val size = clusters._2.size
        //. In the plot make the points belonging to the same cluster have the same color and points belonging to different clusters have different colors.
        // What do you think about the clusters produced by k-means? Are they the "natural" clusters?
        println(s"cluster #$clusterNumber has size of $size elements and has mean of $mean ")
        clusters._2.groupBy(_.specy)
          .foreach { case (specy, elements) =>
            val specyCountInCluster = elements.size
            println(s"cluster #$clusterNumber contains $specyCountInCluster of $specy")
          }
      }
    println(s"*************************End of Run #$index*************************")
  }
}
