package bau.datamining.kmeans.io

/**
  * Created by sercan on 4.12.2016.
  */
class Metrics(var TP: Int, var FP: Int, var FN: Int, var TN: Int) {

  def incrementTP(): Metrics = {
    TP += 1
    this
  }

  def incrementFP(): Metrics = {
    FP += 1
    this
  }

  def incrementFN(): Metrics = {
    FN += 1
    this
  }

  def incrementTN(): Metrics = {
    TN += 1
    this
  }

  def precision(): Double = TP.toDouble / (TP + FP)

  def recall(): Double = TP.toDouble / (TP + FN)

  def errorRate(): Double = (FP + FN).toDouble / (TP + TN + FP + FN)

  def printConsole(): Unit = {
    println(s"precision: $precision, recall: $recall, errorRate: $errorRate")
    println(s"$TP   |   $FN")
    println(s"$FP   |   $TN")
  }

}

object Metrics {
  def apply(actual: Vector[String], predicted: Vector[String], yes: String, no: String): Metrics =
    actual.zip(predicted)
      .foldLeft(new Metrics(0, 0, 0, 0)) {
        case (m, (`yes`, `yes`)) => m.incrementTP()
        case (m, (`no`, `yes`)) => m.incrementFP()
        case (m, (`no`, `no`)) => m.incrementTN()
        case (m, (`yes`, `no`)) => m.incrementFN()
      }

}