name := "vaadin-scala-starter"

version := "0.1"

scalaVersion := "2.12.6"

// "10.0.2", "10.0.0.rc5"
lazy val vaadinVersion = "10.0.2"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.vaadin" % "vaadin-core" % vaadinVersion
)

// "9.3.21.v20170918", "9.4.11.v20180605", "9.4.12.RC1", "9.4.12-SNAPSHOT"
lazy val jettyVersion = "9.4.12.RC1"
containerLibs in Jetty := Seq("org.eclipse.jetty" % "jetty-runner" % jettyVersion intransitive())

enablePlugins(JettyPlugin)
