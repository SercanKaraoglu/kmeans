# kmeans
kmeans scala implementation in 13 lines
```scala
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
```
