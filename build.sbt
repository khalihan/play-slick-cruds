import play.PlayScala

name := "play-slick-cake-sample"

version := "1.0-SNAPSHOT"

lazy val root = Project("play-slick-cake-sample", file("."))
  .enablePlugins(PlayScala)


scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.2")

resolvers += Classpaths.sbtPluginReleases

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

scalacOptions += "-feature"

scalacOptions += "-deprecation"

parallelExecution in Test := false

libraryDependencies ++= {
  val playVersion = "2.3.2"
  val slickVersion = "2.1.0"
  Seq(
    // WebJars pull in client-side web libraries
    jdbc,
    "com.typesafe.play" %% "play" % playVersion,
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.play" %% "play-slick"  %  "0.8.0",
    "mysql" % "mysql-connector-java" % "5.1.31"
  )
}

javaOptions in (Test, test) := Seq("-Xmx256m", "-XX:MaxPermSize=128M")

scalacOptions in (Compile, doc) ++= Seq("-groups", "-implicits")

