package bau.datamining

import bau.datamining.kmeans.io.Row

import scala.collection.parallel.immutable.ParVector

/**
  * Created by sercan on 2.12.2016.
  */
package object kmeans {
  type DataSet = ParVector[Row]
  type ValueSet = ParVector[Any]
  val CATEGORICAL = "C"
  val NUMERIC = "N"
}
