name := "vaadin-scala-starter"

version := "0.1"

scalaVersion := "2.12.6"

lazy val vaadinVersion = "10.0.2"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.vaadin" % "vaadin-core" % vaadinVersion
)

lazy val jettyVersion = "9.4.12.RC1" // "9.4.9.v20180320"
containerLibs in Jetty := Seq("org.eclipse.jetty" % "jetty-runner" % jettyVersion intransitive())

enablePlugins(JettyPlugin)
