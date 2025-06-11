import org.scalajs.dom
import org.scalajs.dom.{html, KeyboardEvent, MouseEvent}
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSGlobal}
import scala.math.{Pi, max, min}
import scala.util.Random

// --- NIVELES DEL JUEGO (PASILLOS ANCHOS) ---
private case class Level(grid: String, message: String)

private object Levels {
  val AllLevels: Map[Int, Level] = Map(
    1 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |W          W
          |W S      E W
          |W          W
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "¡Rebota! ¡Doble salto activado!"
    ),
    2 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWW
          |W            W
          |W S W    W E W
          |W   W WW W   W
          |W   W WW W   W
          |W     WW     W
          |WWWWWWWWWWWWWW
        """.stripMargin,
      message = "Usa los rebotes a tu favor."
    ),
    3 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWW
          |W S WWWWWWWW W
          |W   W        W
          |W   W WWWWWW W
          |W   W W      W
          |W     W    E W
          |WWWWWWWWWWWWWW
        """.stripMargin,
      message = "A veces, el camino es estrecho."
    ),
    4 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWW
          |W E          W W
          |W   WWWWWWWW   W
          |W W W      W W W
          |W W W  S   W W W
          |W W WWWWWWWW W W
          |W W          W W
          |W WWWWWWWWWWWW W
          |W              W
          |WWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "¡Cuidado con las caídas!"
    ),
    5 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWWWW
          |W S W      W   E W
          |W   W WWWW W WWW W
          |W WWW W  W W W W W
          |W   W W  W W W   W
          |W WWW WWWW W WWW W
          |W     W    W     W
          |WWWWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "Laberinto Clásico."
    ),
    6 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |WS         W
          |WWWW  WWWWWW
          |W        W W
          |WWWWWWWW W W
          |W          W
          |W WWWWWWWW E
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "Elige tu camino."
    ),
    7 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |W          W
          |W WW WW WW W
          |W  S      E W
          |W WW WW WW W
          |W          W
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "Plataformas de salto."
    ),
    8 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWW
          |W S          W E
          |W W WWWWWWWW W W
          |W W W      W W W
          |W W W WWWW W W W
          |W W W W  W W W W
          |W   W    W   W W
          |WWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "Largo recorrido."
    ),
    9 -> Level(
      grid =
        """
          |W W W WWWWWWWW
          |W W W W      E
          |W W W W WWWWWW
          |W W W W      W
          |W W W WWWWWW W
          |W W W        W
          |W S WWWWWWWWWW
          |WWWWWWWWWWWWWW
        """.stripMargin,
      message = "Zig-zag."
    ),
    10 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWW
          |W              W
          |W  S         W W
          |W WWWWWWWWWW W W
          |W W          W W
          |W W WWWWWWWWWW W
          |W W            W
          |W WWWWWWWWWW W W
          |W            E W
          |WWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "La gran serpiente."
    ),
    11 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |W    S     W
          |W WWWWWWWW W
          |W W        W
          |W W WWWWWWWW
          |W W W      W
          |W W   E    W
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "Caída controlada."
    ),
    12 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |W SWWWWWWW E
          |W W        W
          |W WWWWWWWW W
          |W        W W
          |WWWWWWWW W W
          |W          W
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "Un atajo... ¿o una trampa?"
    ),
    13 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |W S        W
          |W WWWWWW   W
          |W      W   W
          |W   WWWWWW W
          |W   W      W
          |W   W    E W
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "Escalera."
    ),
    14 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWW
          |W W W W W W W EW
          |W              W
          |W WWWWWWWWWWWW W
          |W W            W
          |W WWWWWWWWWWWW W
          |W              W
          |W WWWWWWWWWWWW W
          |W S            W
          |WWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "Paciencia y saltos precisos."
    ),
    15 -> Level(
      grid =
        """
          |WWWWWWWWWW
          |W        W
          |W WWWWWW W
          |W W S  W W
          |W W WW W W
          |W W E  W W
          |W WWWWWW W
          |W        W
          |WWWWWWWWWW
        """.stripMargin,
      message = "El centro."
    ),
    16 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWW
          |W S W          W
          |W W W WWWWWWWW W
          |W W W W      W W
          |W   W   WWWW W W
          |WWWWWWWWWW W W W
          |W          W W E
          |WWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "Casi allí."
    ),
    17 -> Level(
      grid =
        """
          |WWWWWWWWWW
          |W        W
          |W W WWWW W
          |W W S  W W
          |W WWWW W W
          |W W    W W
          |W W WWWW W
          |W        W
          |WWWWWWWEWW
        """.stripMargin,
      message = "La trampa del pasillo."
    ),
    18 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWW
          |W              W
          |W WWWWWWWWWWWW W
          |W W S        W W
          |W W WWWWWWWW W W
          |W W          W W
          |W WWWWWWWWWW W W
          |W W          E W
          |W WWWWWWWWWWWW W
          |W              W
          |WWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "Laberinto oscuro."
    ),
    19 -> Level(
      grid =
        """
          |WWWWWWWWWWWW
          |W S        W
          |W  WWWWW W W
          |WW W     W W
          |W  W WWWWW W
          |W    W   E W
          |WWWWWWWWWWWW
        """.stripMargin,
      message = "El penúltimo desafío."
    ),
    20 -> Level(
      grid =
        """
          |WWWWWWWWWWWWWWWWWW
          |W                W
          |W WWWWWWWWWWWWWW W
          |W W   S      E W W
          |W W WWWWWWWWWW W W
          |W W            W W
          |W WWWWWWWWWWWWWW W
          |W                W
          |WWWWWWWWWWWWWWWWWW
        """.stripMargin,
      message = "¡La arena final! ¡HAS GANADO!"
    )
  )
  def getLevel(levelNumber: Int): Option[Level] = AllLevels.get(levelNumber)
}

// --- FACHADA DE THREE.JS ---
@js.native
@JSGlobal("THREE.Vector2")
private class Vector2(var x: Double, var y: Double) extends js.Object

@js.native
@JSGlobal("THREE.Vector3")
private class Vector3(var x: Double, var y: Double, var z: Double) extends js.Object {
  def this() = this(0, 0, 0)
  def add(v: Vector3): this.type = js.native
  def sub(v: Vector3): this.type = js.native
  override def clone(): Vector3 = js.native
  def copy(v: Vector3): this.type = js.native
  def set(x: Double, y: Double, z: Double): this.type = js.native
  def cross(v: Vector3): this.type = js.native
  def normalize(): this.type = js.native
  def distanceTo(v: Vector3): Double = js.native
  def multiplyScalar(s: Double): this.type = js.native
}

@js.native
@JSGlobal("THREE.Euler")
private class Euler(var x: Double, var y: Double, var z: Double, var order: String) extends js.Object {
  def set(x: Double, y: Double, z: Double): this.type = js.native
}

@js.native
@JSGlobal("THREE.Object3D")
private class Object3D extends js.Object {
  var position: Vector3 = js.native
  var rotation: Euler = js.native
  var quaternion: js.Dynamic = js.native
  var castShadow: Boolean = js.native
  var receiveShadow: Boolean = js.native
  var visible: Boolean = js.native
  var up: Vector3 = js.native
  def add(obj: Object3D): Unit = js.native
  def remove(obj: Object3D): Unit = js.native
  def getWorldDirection(target: Vector3): Vector3 = js.native
}

@js.native
@JSGlobal("THREE.Mesh")
private class Mesh(geometry: js.Any, material: js.Any) extends Object3D

@js.native
@JSGlobal("THREE.Camera")
private class Camera extends Object3D

@js.native
@JSGlobal("THREE.PerspectiveCamera")
private class PerspectiveCamera(fov: Double, aspect: Double, near: Double, far: Double) extends Camera

@js.native
@JSGlobal("THREE.Box3")
private class Box3 extends js.Object {
  def setFromObject(obj: Object3D): this.type = js.native
  def intersectsBox(box: Box3): Boolean = js.native
}

@js.native
@JSGlobal("THREE.Clock")
private class Clock(autoStart: Boolean) extends js.Object {
  def getDelta(): Double = js.native
}

@js.native
@JSGlobal("THREE.Scene")
private class Scene extends Object3D

@js.native @JSGlobal("THREE")
private object THREE extends js.Object {
  val AmbientLight: js.Dynamic = js.native
  val DirectionalLight: js.Dynamic = js.native
  val WebGLRenderer: js.Dynamic = js.native
  val SphereGeometry: js.Dynamic = js.native
  val BoxGeometry: js.Dynamic = js.native
  val PlaneGeometry: js.Dynamic = js.native
  val MeshStandardMaterial: js.Dynamic = js.native
  val MeshBasicMaterial: js.Dynamic = js.native
}


@JSExportTopLevel("RumbleBall1P")
object RumbleBall1P {

  def main(args: Array[String]): Unit = {
    dom.window.onload = (_: dom.Event) => setupGame()
  }

  def setupGame(): Unit = {
    val canvas = dom.document.getElementById("game-canvas").asInstanceOf[html.Canvas]
    val levelDisplay = dom.document.getElementById("level-display").asInstanceOf[html.Div]
    val messageBox = dom.document.getElementById("message-box").asInstanceOf[html.Div]

    val scene = new Scene()
    val camera = new PerspectiveCamera(75, canvas.clientWidth.toDouble / canvas.clientHeight.toDouble, 0.1, 1000)
    val renderer = js.Dynamic.newInstance(THREE.WebGLRenderer)(js.Dynamic.literal(canvas = canvas, antialias = true))
    renderer.setSize(canvas.clientWidth, canvas.clientHeight)
    renderer.shadowMap.enabled = false

    val ambientLight = js.Dynamic.newInstance(THREE.AmbientLight)(0x808080, 1.5).asInstanceOf[Object3D]
    scene.add(ambientLight)

    val keyLight = js.Dynamic.newInstance(THREE.DirectionalLight)(0xffffff, 1.0).asInstanceOf[Object3D]
    keyLight.position.set(15, 20, 10)
    scene.add(keyLight)

    var currentLevel = 1
    val obstacles: js.Array[Mesh] = new js.Array()
    var player: Mesh = null
    var exitPosition: Vector3 = null

    val playerVelocity = new Vector3(0, 0, 0)
    val playerSpeed = 15.0
    val jumpStrength = 12.0
    var jumpCount = 0
    val gravity = -35.0
    val mouseSensitivity = 0.002
    val bounceFactor = -0.7

    val keysPressed = js.Dynamic.literal(w = false, a = false, s = false, d = false)
    var spaceJustPressed = false

    // --- PALETAS DE COLORES PARA 20 NIVELES ---
    val levelColorPalettes: Map[Int, Seq[Int]] = Map(
      1 -> Seq(0x8B4513, 0xA0522D, 0x7E5835, 0x6B4423), // Naranjas/Ladrillo
      2 -> Seq(0x483D8B, 0x8A2BE2, 0x9400D3, 0x4B0082), // Violetas/Morados
      3 -> Seq(0x778899, 0x708090, 0x696969, 0x808080), // Grises/Piedra
      4 -> Seq(0x2E8B57, 0x3CB371, 0x228B22, 0x006400), // Verdes
      5 -> Seq(0xB22222, 0x8B0000, 0xA52A2A, 0xDC143C), // Rojizos
      6 -> Seq(0x00CED1, 0x20B2AA, 0x48D1CC, 0x40E0D0), // Turquesas
      7 -> Seq(0x4682B4, 0x5F9EA0, 0x6495ED, 0x00BFFF), // Azules Acero
      8 -> Seq(0xFFD700, 0xFFC300, 0xFFB900, 0xFFA500), // Dorados/Amarillos
      9 -> Seq(0xFF6347, 0xFF4500, 0xFF7F50, 0xFFA07A), // Corales
      10 -> Seq(0x7FFF00, 0xADFF2F, 0x32CD32, 0x00FF00),// Verdes Lima
      11 -> Seq(0xDA70D6, 0xEE82EE, 0xFF00FF, 0xBA55D3),// Orquídeas/Magentas
      12 -> Seq(0x191970, 0x000080, 0x00008B, 0x4169E1),// Azules Oscuros
      13 -> Seq(0xF4A460, 0xCD853F, 0xD2691E, 0x8B4513),// Arenas/Chocolate
      14 -> Seq(0xFF4500, 0xFF6347, 0xF08080, 0xE9967A),// Rojo/Naranja
      15 -> Seq(0x00FFFF, 0x00BFFF, 0x008B8B, 0x00CED1),// Cianes
      16 -> Seq(0xFFC0CB, 0xFFB6C1, 0xFF69B4, 0xDB7093),// Rosados
      17 -> Seq(0x9370DB, 0x8A2BE2, 0x9932CC, 0x8B008B),// Morados Medios
      18 -> Seq(0x000000, 0x1C1C1C, 0x2F4F4F, 0x36454F),// Grises/Negros
      19 -> Seq(0x3CB371, 0x2E8B57, 0x66CDAA, 0x8FBC8F),// Verdes Mar
      20 -> Seq(0xFFD700, 0xFFF8DC, 0xFFE4B5, 0xFFFACD) // Dorado y Blanco
    )

    def restartGame(): Unit = {
      showMessage("¡Has caído! Reiniciando...", 2000)
      currentLevel = 1
      loadLevel(currentLevel)
    }

    def loadLevel(levelNumber: Int): Unit = {
      obstacles.foreach(obs => scene.remove(obs))
      obstacles.length = 0

      Levels.getLevel(levelNumber).foreach { levelData =>
        levelDisplay.innerHTML = s"Nivel: $levelNumber"

        val wallHeight = 2.0
        val wallYPos = wallHeight / 2
        val wallGeometry = js.Dynamic.newInstance(THREE.BoxGeometry)(1, wallHeight, 1)
        val colors = levelColorPalettes.getOrElse(levelNumber, levelColorPalettes(1))

        val gridRows = levelData.grid.split('\n').filter(_.trim.nonEmpty)
        val gridHeight = gridRows.length
        val gridWidth = gridRows(0).length

        // --- CORRECCIÓN: SUELO DEL TAMAÑO DEL NIVEL ---
        val corridorWidth = 2.0
        val floorWidth = gridWidth * corridorWidth
        val floorHeight = gridHeight * corridorWidth
        val groundGeo = js.Dynamic.newInstance(THREE.PlaneGeometry)(floorWidth, floorHeight)
        val groundMat = js.Dynamic.newInstance(THREE.MeshStandardMaterial)(js.Dynamic.literal(color = 0x333333, roughness = 1.0))
        val ground = new Mesh(groundGeo, groundMat)
        ground.rotation.x = -Pi / 2
        scene.add(ground)
        obstacles.push(ground)
        // --- FIN DE LA CORRECCIÓN ---

        gridRows.zipWithIndex.foreach { case (row, y) =>
          row.zipWithIndex.foreach { case (char, x) =>
            val pos_x = (x.toDouble - gridWidth / 2.0 + 0.5) * corridorWidth
            val pos_z = (y.toDouble - gridHeight / 2.0 + 0.5) * corridorWidth

            char match {
              case 'W' =>
                val wallMaterial = js.Dynamic.newInstance(THREE.MeshStandardMaterial)(
                  js.Dynamic.literal(color = colors(Random.nextInt(colors.length)), roughness = 0.7)
                )
                val wall = new Mesh(wallGeometry, wallMaterial)
                wall.position.set(pos_x, wallYPos, pos_z)
                scene.add(wall)
                obstacles.push(wall)
              case 'S' =>
                if (player == null) {
                  val playerGeometry = js.Dynamic.newInstance(THREE.SphereGeometry)(0.5)
                  val playerMaterial = js.Dynamic.newInstance(THREE.MeshBasicMaterial)()
                  player = new Mesh(playerGeometry, playerMaterial)
                  player.visible = false
                  scene.add(player)
                  player.add(camera)
                }
                player.position.set(pos_x, 0.5, pos_z)
                playerVelocity.set(0, 0, 0)
                jumpCount = 0
                camera.position.set(0, 0, 0)
                camera.rotation.set(0, 0, 0)
                player.rotation.set(0, 0, 0)
              case 'E' =>
                val exitGeo = js.Dynamic.newInstance(THREE.BoxGeometry)(1.6, 0.2, 1.6)
                val exitMat = js.Dynamic.newInstance(THREE.MeshBasicMaterial)(js.Dynamic.literal(color = 0x00ff00))
                val exitMesh = new Mesh(exitGeo, exitMat)
                exitMesh.position.set(pos_x, 0.1, pos_z)
                scene.add(exitMesh)
                exitPosition = exitMesh.position.clone()
              case _ =>
            }
          }
        }
      }
    }

    canvas.onclick = (_: MouseEvent) => canvas.requestPointerLock()

    dom.document.onkeydown = (e: KeyboardEvent) => {
      handleKey(e.key, pressed = true)
      if (e.key == " ") {
        if (!spaceJustPressed) handleJump()
        spaceJustPressed = true
      }
    }
    dom.document.onkeyup = (e: KeyboardEvent) => {
      handleKey(e.key, pressed = false)
      if (e.key == " ") spaceJustPressed = false
    }

    def handleKey(key: String, pressed: Boolean): Unit = {
      key.toLowerCase() match {
        case "s" => keysPressed.updateDynamic("w")(pressed)
        case "w" => keysPressed.updateDynamic("s")(pressed)
        case "a" => keysPressed.updateDynamic("d")(pressed)
        case "d" => keysPressed.updateDynamic("a")(pressed)
        case _ =>
      }
    }

    def handleJump(): Unit = {
      if (jumpCount < 2) {
        playerVelocity.y = jumpStrength
        jumpCount += 1
      }
    }

    dom.document.onmousemove = (e: MouseEvent) => {
      if (dom.document.pointerLockElement == canvas && player != null) {
        player.rotation.y -= e.movementX * mouseSensitivity
        camera.rotation.x -= e.movementY * mouseSensitivity
        camera.rotation.x = max(-Pi / 2, min(Pi / 2, camera.rotation.x))
      }
    }

    def showMessage(text: String, duration: Int): Unit = {
      messageBox.innerHTML = text
      messageBox.style.display = "block"
      dom.window.setTimeout(() => messageBox.style.display = "none", duration)
    }

    val clock = new Clock(true)

    def animate(): Unit = {
      dom.window.requestAnimationFrame((_: Double) => animate())
      val deltaTime = clock.getDelta()

      if (player != null) {
        val playerDirection = new Vector3()
        player.getWorldDirection(playerDirection)
        val rightDirection = new Vector3().copy(playerDirection).cross(player.up)

        val moveInput = new Vector3()
        if (keysPressed.w.asInstanceOf[Boolean]) moveInput.add(playerDirection)
        if (keysPressed.s.asInstanceOf[Boolean]) moveInput.sub(playerDirection)
        if (keysPressed.d.asInstanceOf[Boolean]) moveInput.add(rightDirection)
        if (keysPressed.a.asInstanceOf[Boolean]) moveInput.sub(rightDirection)

        playerVelocity.x = moveInput.normalize().x * playerSpeed
        playerVelocity.z = moveInput.normalize().z * playerSpeed

        playerVelocity.y += gravity * deltaTime

        val oldPos = player.position.clone()
        val moveVector = playerVelocity.clone().multiplyScalar(deltaTime)

        player.position.x += moveVector.x
        if (obstacles.exists(obstacleCollides)) {
          player.position.x = oldPos.x
          playerVelocity.x *= bounceFactor
        }

        player.position.z += moveVector.z
        if (obstacles.exists(obstacleCollides)) {
          player.position.z = oldPos.z
          playerVelocity.z *= bounceFactor
        }

        player.position.y += moveVector.y
        if (obstacles.exists(obstacleCollides)) {
          player.position.y = oldPos.y
          if (playerVelocity.y < 0) jumpCount = 0
          playerVelocity.y *= bounceFactor
        }

        if (player.position.y < -10) {
          restartGame()
          return
        }

        if (exitPosition != null && player.position.distanceTo(exitPosition) < 1.5) {
          currentLevel += 1
          if (Levels.AllLevels.contains(currentLevel)) loadLevel(currentLevel)
          else {
            showMessage("¡HAS GANADO EL JUEGO!", 10000)
            dom.document.exitPointerLock()
          }
        }
      }
      renderer.render(scene, camera)
    }

    def obstacleCollides(obstacle: Mesh): Boolean = {
      val playerBox = new Box3().setFromObject(player)
      val obstacleBox = new Box3().setFromObject(obstacle)
      obstacleBox.intersectsBox(playerBox)
    }

    loadLevel(currentLevel)
    animate()
  }
}
