name := "vaadin-scala-starter"

version := "0.1"

scalaVersion := "2.12.6"

lazy val vaadinVersion = "10.0.2"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.vaadin" % "vaadin-core" % vaadinVersion
)

enablePlugins(JettyPlugin)
