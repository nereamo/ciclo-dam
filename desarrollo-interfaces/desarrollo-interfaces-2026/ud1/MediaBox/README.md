# 📥 MediaBox
> Aplicación de escritorio en Java para la gestión, descarga y reproducción de contenidos multimedia y audio.

**Autor:** Nerea Montoya

---

## 📜 Historial de Commits
<details>
<summary>Ver captura del historial de commits</summary>

<br>
<img width="2560" height="1392" alt="Historial de Commits" src="https://github.com/user-attachments/assets/a5db15ff-f833-413e-afc3-7fe6e1d75acb" />
</details>

---

## 🚀 Funcionalidades Principales

* **📋 Pegar desde Portapapeles:** Permite capturar la URL directamente utilizando el botón "Paste" de la interfaz principal.
* **📊 Barra de Progreso:** Muestra el estado y avance de la descarga en tiempo real mediante un `JProgressBar`.
* **▶️ Reproductor Integrado:** Permite abrir y reproducir cualquier archivo multimedia seleccionado directamente desde la tabla.
* **⬇️ Gestión con API:** Botón dedicado para la descarga de archivos remotos desde la API.
* **⬆️ Subida de Archivos:** Permite subir recursos locales hacia la API de manera sencilla.
* **🔄 Sincronización Automática (Polling):** 
  * El componente `MediaPollingComponent` consulta periódicamente la API para notificar la existencia de nuevos archivos.
  * Al iniciar sesión, el sistema obtiene el token del usuario e inicia el ciclo de sondeo (*polling*).
  * Desde las propiedades de diseño de NetBeans, es posible configurar el intervalo de tiempo (`pollingInterval`) o desactivar la función (`running`).

---

## ⚠️ Resolución de Problemas y Retos Técnicos

### 🐞 1. Descarga en formato Webm
* **Problema:** Al descargar un vídeo en formato Webm y posteriormente descargar el mismo recurso en formato de audio, el segundo sobrescribía al archivo original webm debido a la colisión de nombres.
* **Solución:** Modificación de la nomenclatura añadiendo un sufijo identificativo al descargar audio: `%(title)s_audio.%(ext)s`.

### 🐞 2. Transición entre Paneles (Login, Frame y Preferences)
* **Problema:** Al incrustar el panel de login dentro del marco principal, las vistas secundarias de preferencias no lograban renderizar ni visualizar sus componentes correctamente.
* **Solución:** Migración de un layout nulo a un contenedor **`CardLayout`**, agrupando todos los `JPanels` bajo este gestor para alternar entre vistas de forma fluida.

### 🐞 3. Parpadeo Visual en Celdas de Acción (Flash Blanco)
* **Problema:** Al interactuar con los botones situados en la columna de acciones de la tabla, las celdas experimentaban un parpadeo visual molesto (*flash* en blanco).
* **Solución:** Sustitución de los botones estándar por componentes `JLabel` interactivos, preservando intacto el diseño y el *Look & Feel* (`FlatLaf`) establecido.

---

## 🎨 Usabilidad y Experiencia de Usuario (UX/UI)

### 🎨 Aspecto visual e interfaz
* **Diseño Moderno:** Interfaz de tonos oscuros (grises y púrpuras) optimizada para reducir la fatiga visual.
* **Jerarquía de Color:** El color púrpura destaca las acciones principales frente a las secundarias (botones de descarga, selección de directorios, búsqueda de `yt-dlp.exe`, subida de archivos y login).
* **Distribución:** Flujo vertical estructurado (`URL` ➡️ `Directorio` ➡️ `Formato` ➡️ `Descarga` ➡️ `Progreso` ➡️ `Historial/Tabla`).
* **Tecnologías UI:** Implementación de **`MigLayout`** para una distribución flexible y la librería **`FlatLaf`** para un aspecto moderno tipo *Material/Dark*.

### 🧩 Affordance, Feedback y Restricciones
* **Affordance:** 
  * Puntero en forma de mano (`HAND_CURSOR`) en todos los elementos interactivos.
  * Campos de texto con textos guía (*placeholders*) e iconos descriptivos que facilitan la comprensión de las acciones.
* **Feedback Dinámico:** 
  * Mensajes emergentes de confirmación para acciones críticas (cerrar aplicación, eliminar archivos locales o descartar cambios en preferencias).
  * Notificaciones en interfaz para el estado del login, la detección automática de `yt-dlp.exe` y avisos de validación (por ejemplo, exigir seleccionar un directorio antes de iniciar la descarga).
* **Restricciones de Seguridad/Flujo:** El sistema bloquea las acciones de descarga si no se han completado los pasos previos obligatorios (credenciales, URL válida, ruta de destino y formato seleccionado).

### 🧩 Mejoras adicionales de UX
* Iconos inteligentes en inputs para vaciar texto, alternar visibilidad de contraseñas o pegar desde el portapapeles.
* Uso generalizado de *Tooltips* informativos.
* Ventanas totalmente redimensionables manteniendo la proporción de sus componentes.

---

## 📝 Gestión de Errores y Logs
* **Validación de Entradas:** Comprobación rigurosa de los datos introducidos en formularios.
* **Control de Excepciones:** Implementación de bloques `try-catch` estructurados en procesos críticos de E/S y red.
* **Registro de Errores (Logs):** Sistema de registro interno para trazar fallos de ejecución sin bloquear ni congelar la interfaz de usuario.

---

## 🌐 Recursos y Referencias

### 🎬 Vídeos de Apoyo
* [Guía de diseño de interfaz y componentes](https://youtu.be/SOKLL0S1RQo?si=dWPB2aIZPc1xYAAL)

### 🔍 Documentación Técnica e Hilos
* **Concurrencia (Threads):**
  * [Hilos y Thread en Java](https://programandoenjava.com/hilos-thread-en-java/)
  * [Cómo terminar o eliminar un hilo por completo en Java](https://es.stackoverflow.com/questions/41453/c%C3%B3mo-terminar-o-eliminar-un-hilo-por-completo-en-java)
* **Manipulación JSON:**
  * [Java y JSON con la librería Jackson](https://www.arquitecturajava.com/java-json-con-jackson/)

### 🤖 Asistencias de Inteligencia Artificial
* **GitHub Copilot:**
  * Implementación de la concurrencia asíncrona mediante `SwingWorker`.
* **Gemini:**
  * Propuesta de múltiples extensiones y formatos de exportación de descarga.
  * Lógica para la localización y resolución automática de la ruta del ejecutable `yt-dlp.exe`.
    - La alicación registra información de los fallos ocurridos durante la ejecución sin bloquear la interfaz.
