name         := "ESON Mining"
version      := "0.1.0-SNAPSHOT"
organization := "ca.uwaterloo.dsg"

scalaVersion := "2.12.2"

mainClass in Compile := Some("ca.uwaterloo.dsg.eson.DiscoveryRunner")

resolvers += Resolver.mavenLocal
resolvers += "michaelmior-Metanome" at "https://packagecloud.io/michaelmior/Metanome/maven2"

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "4.0.0-RC2",
  "com.google.guava" % "guava" % "23.6-jre",
  "de.metanome" % "algorithm_integration" % "1.2-calcite-SNAPSHOT",
  "de.metanome" % "backend" % "1.2-calcite-SNAPSHOT",
  "de.metanome.algorithms.spider" % "SPIDERAlgorithm" % "1.2-calcite-SNAPSHOT",
  "de.metanome.algorithms.spider" % "SPIDER" % "1.2-calcite-SNAPSHOT",
  "de.uni-potsdam.hpi" % "dao" % "0.0.1-calcite-SNAPSHOT",
  "de.uni-potsdam.hpi" % "utils" % "0.0.1-calcite-SNAPSHOT",

  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

logBuffered in Test := false

assemblyMergeStrategy in assembly := {
  case PathList("asm-license.txt") => MergeStrategy.discard
  case PathList("LICENSE") => MergeStrategy.discard
  case PathList("NOTICE") => MergeStrategy.discard
  case PathList("META-INF", xs @_ *) => MergeStrategy.discard
  case PathList("com", "fasterxml", "jackson", xs @ _*) => MergeStrategy.first
  case PathList("com", "google", "common", xs @ _*) => MergeStrategy.first
  case PathList("com", "google", "guava", xs @ _*) => MergeStrategy.first
  case PathList("com", "google", "thirdparty", xs @ _*) => MergeStrategy.first
  case PathList("com", "mysql", "jdbc", xs @ _*) => MergeStrategy.first
  case PathList("javax", "annotation", xs @ _*) => MergeStrategy.first
  case PathList("org", "gjt", "mm", "mysql", xs @ _*) => MergeStrategy.first
  case PathList("org", "hamcrest", xs @ _*) => MergeStrategy.first
  case PathList("org", "xml", "sax", xs @ _*) => MergeStrategy.first
  case _ => MergeStrategy.deduplicate
}

fork in run := true
