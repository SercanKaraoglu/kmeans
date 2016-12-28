name := "kmeans"

version := "1.0"

scalaVersion := "2.12.0"

resolvers ++= Seq(
  "MavenRepository" at "https://mvnrepository.com/",
  "Confluent Maven Repo" at "http://packages.confluent.io/maven/",
  "Artima Maven Repository" at "http://repo.artima.com/releases",
  Resolver.sonatypeRepo("public"),
  Resolver typesafeRepo "releases"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"