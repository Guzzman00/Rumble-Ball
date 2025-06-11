# **Rumble Ball**

¡Bienvenido a Rumble Ball\! Un vertiginoso juego de plataformas y laberintos en primera persona donde la habilidad, los rebotes y el doble salto son la clave para encontrar la salida. Este proyecto fue desarrollado completamente en **Scala** y transpilado a **JavaScript** para correr en el navegador gracias a la magia de **Scala.js**.

## **🎮 Características Principales**

* **Física de Rebote:** ¡No te quedes atascado\! Choca contra las paredes para rebotar y cambiar de dirección de forma dinámica.  
* **Controles FPS:** Muévete con total libertad en un entorno 3D con controles inspirados en los shooters en primera persona.  
* **20 Niveles Desafiantes:** Supera 20 laberintos diseñados a mano, cada uno con su propia atmósfera y paleta de colores.  
* **Doble Salto:** Alcanza plataformas lejanas y sortea obstáculos con la mecánica de doble salto.  
* **Muerte por Caída:** ¡Cuidado por dónde pisas\! Si te caes del laberinto, el juego se reinicia.  
* **Banda Sonora Dinámica:** Disfruta de una selección de música con controles en pantalla para cambiar de canción.  
* **Hecho con Scala.js:** Todo el código del juego, desde la lógica hasta la física y el renderizado, está escrito en Scala y corre de forma nativa en cualquier navegador moderno.

## **📜 Versiones Anteriores (Lost Media)**

El camino para llegar a la versión final fue largo. Aquí hay un vistazo a los prototipos anteriores desarrollados en otras tecnologías.

Versión en Rust (Arte ASCII) | 
https://github.com/Guzzman00/Rumble-Ball/blob/main/d7b1ba03-1480-457f-8db0-6c0dd5fe8d41.jpg

Versión en Java (Prototipo 2D) | 
https://github.com/Guzzman00/Rumble-Ball/blob/main/0de340e0-1277-48cc-bce9-ebfb4b137fc0.jpg

Versión en JavaScript (primera persona y tercera persona para hacer pruebas, esta última en foto no está disponible) | 
https://github.com/Guzzman00/Rumble-Ball/blob/main/df1e730b-c769-4ff5-9994-1756bde339ed.jpg

Versión en Scala.js |
https.github.com/Guzzman00/Rumble-Ball/blob/main/6fe4bb61-a6c7-4f37-9717-0f592b76fd19.jpg 

## **🚀 Cómo Jugar**

Para ejecutar el proyecto en tu máquina local, sigue estos pasos.

### **Requisitos Previos**

1. **JDK 17:** Se recomienda Azul Zulu 17.0.14.  
   * [Descargar Azul Zulu 17.0.14 para Windows](https://www.azul.com/downloads/?version=java-17-lts&package=jdk-fx#zulu)  

2. **SBT 1.10.10:** La herramienta para construir proyectos de Scala.  
   * [Descargar SBT 1.10.10 para Windows (MSI)](https://github.com/sbt/sbt/releases/download/v1.10.1/sbt-1.10.1.msi) (Nota: Es retrocompatible con versiones anteriores del SBT, pero adjunto uno más antiguo por si es necesario: [Descargar SBT 1.8.3 para Windows (MSI)](https://github.com/sbt/sbt/releases/download/v1.8.3/sbt-1.8.3.msi))

### **Instrucciones de Ejecución**

1. **Clonar el Repositorio:** Abre una terminal o Git Bash y clona el proyecto.  
   git clone https://github.com/Guzzman00/Rumble-Ball.git  
   cd Rumble-Ball

2. **Abrir con un IDE:** Abre la carpeta del proyecto con IntelliJ IDEA para explorar el código.  

3. **Transpilar a JavaScript con SBT:** Dentro de la carpeta del proyecto, ejecuta la terminal de SBT.
    fastOptJS

5. **Desplegar localmente con Deno:** Dentro de la carpeta del proyecto, ejecuta la terminal.   
    deno run --allow-net --allow-read https://deno.land/std/http/file_server.ts
  
6. **Abrir el Juego:** Ingresa con el link Network que proporciona Deno.
  
7. **¡A Jugar\!:** Haz clic en la ventana del juego para empezar.

## **🛠️ Stack Tecnológico**

* **Lenguaje:** Scala 2.13.13  
* **Transpilador:** Scala.js 1.16.0  
* **Motor Gráfico:** Three.js (r128)  
* **Herramienta de Build:** SBT 1.10.10
* **Servidor local:** Deno 2.2.3