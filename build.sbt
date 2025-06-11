// --- Configuración General del Proyecto ---
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"

// --- Configuración de Scala ---
// ACTUALIZADO: Usamos la versión 2.13.13 que nos pide la dependencia.
ThisBuild / scalaVersion := "2.13.13"

// --- Configuración del Proyecto Principal (rumble-ball) ---
lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin) // Activamos el plugin de Scala.js primero.
  .settings(
    // Agrupamos todas las configuraciones en un único bloque .settings()
    name := "rumble-ball",

    // Dependencia para interactuar con el DOM del navegador
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.0",

    // --- Configuración específica de Scala.js ---
    // Le decimos a Scala.js dónde está el punto de entrada de la aplicación.
    scalaJSUseMainModuleInitializer := true,
    mainClass := Some("rumbleball.RumbleBall1P")
  )
