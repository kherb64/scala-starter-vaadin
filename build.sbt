name := "vaadin-scala-starter"

version := "0.1"

scalaVersion := "2.12.6"

val vaadinVersion = "10.0.2"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.vaadin" % "vaadin-core" % vaadinVersion
)

containerLibs in Jetty := Seq("org.eclipse.jetty" % "jetty-runner" % "9.3.21.v20170918" intransitive())

enablePlugins(JettyPlugin)
