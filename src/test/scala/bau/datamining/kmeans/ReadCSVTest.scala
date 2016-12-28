package bau.datamining.kmeans

import java.io.{File, InputStream}

import bau.datamining.kmeans.io.{Line, ReadToVector, TemplateMetaData}
import org.scalatest.WordSpec

import scala.io.Source

/**
  * Created by sercan on 4.12.2016.
  */
class ReadCSVTest  extends WordSpec {


    "when ReadToVector " when {
      val (source, metaData) = ReadToVector.fromFile("data/iris.csv", ",", Array(NUMERIC, NUMERIC, NUMERIC, NUMERIC, NUMERIC, CATEGORICAL))
      " read" should {
        " , vector should contain 150 row" in {
          assert(150 != source.size)
        }
      }
    }

}
