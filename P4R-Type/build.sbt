val scala3Version = "3.1.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "p4rt-scala",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)

// Silence warnings due to ScalaPB-generated code
scalacOptions ++= Seq("-Wconf:cat=deprecation&msg=Marked as deprecated in proto file:silent")

// (optional) If you need scalapb/scalapb.proto or anything from
// google/protobuf/*.proto
libraryDependencies ++= Seq(
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
)

libraryDependencies ++= Seq(
    "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

Compile / PB.targets := Seq(
    scalapb.gen(grpc = true) -> (Compile / sourceManaged).value / "scalapb",
    scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
)

libraryDependencies ++= Seq(
    "io.grpc" % "grpc-netty" % "1.41.0",
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

libraryDependencies += "dev.zio" %% "zio" % "2.0.0"

libraryDependencies += "com.thesamet.scalapb" %% "scalapb-json4s" % "0.12.0"
