name := "vaadin-scala-starter"

version := "0.1"

scalaVersion := "2.12.6"

resolvers +=
  "Vaadin Prereleases" at "https://maven.vaadin.com/vaadin-prereleases"

libraryDependencies ++= Seq(
  "com.vaadin" % "flow-data" % "1.0.0.rc5",
  "com.vaadin" % "vaadin-button-flow" % "1.0.0.rc1",
  "com.vaadin" % "vaadin-combo-box-flow" % "1.0.0.rc3",
  "com.vaadin" % "vaadin-form-layout-flow" % "1.0.0.rc1",
  "com.vaadin" % "vaadin-grid-flow" % "1.0.0.rc2",
  "com.vaadin" % "vaadin-icons-flow" % "1.0.0.rc1",
  "com.vaadin" % "vaadin-ordered-layout-flow" % "1.0.0.rc1",
  "com.vaadin" % "vaadin-text-field-flow" % "1.0.0.rc1"
  )

/*
//noinspection Annotator
// Vaadin 8.1 has problems with default jetty in plugin :-(
// https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-runner
val jettyLibs = Seq("org.eclipse.jetty" % "jetty-runner" % "9.3.21.v20170918" intransitive())

// special Jetty settings
containerLibs in Jetty := jettyLibs
*/

enablePlugins(JettyPlugin)
