# 🏋️ ProTrainer

Aplicación de Escritorio en Java (Swing) orientada a la gestión integral de entrenamientos, usuarios y ejercicios para instructores, diseñada bajo una arquitectura modular y con una interfaz optimizada para mejorar la experiencia de usuario (*UX/UI*).

---

## 🛠️ Tecnologías y Requisitos Técnicos

* **Lenguaje:** Java
* **Interfaz Gráfica:** Java Swing (con gestor de diseño `MigLayout` para asegurar la escalabilidad de la interfaz).
* **Gestión de Base de Datos / Lógica:** Patrón DAO (*Data Access Object*) para separar la lógica de negocio y persistencia.
* **Componentes Externos:** Requiere el componente personalizado **`ComponentCalendar`** para la vista de calendarios.

## 📐 Diseño y Estructura de la Interfaz

### 1. Ventana Principal (`ProTrainer` - JFrame Main)

![Captura de pantalla 2025-03-25 182332](https://github.com/user-attachments/assets/d057d0ea-3818-47b0-8b7c-2e4e58aeac31)

* **`jMnuBar`**
  * `mnuOpciones`: Opciones de cierre de sesión (*Logout*) y salida de la aplicación (*Exit*).
  * `mnuAbout`: Contiene la información del autor del proyecto.
* **`pnlMain`**
  * `lblLogo`: Logotipo principal de la aplicación.
  * `btnAcceso`: Despliega el `DialogLogin` para ingresar credenciales.
  * `lblUrl`: Icono con enlace directo a la página web corporativa.

---

### 2. Diálogo de Inicio de Sesión (`DialogLogin`)

![Captura de pantalla 2025-03-24 141601](https://github.com/user-attachments/assets/c72671bc-86bd-49fe-b5b4-f580f6ed72e0)

* `txtFieldEmail`: Campo para escribir el correo electrónico.
* `fldPassword`: Campo para introducir la contraseña de acceso.
* `jChkMostrarContraseña`: Permite alternar la visibilidad de la contraseña.
* `lblMsg`: Etiqueta de validación visual de errores en las credenciales.
* `btnLogin`: Botón de validación y acceso al sistema.

---

### 3. Panel Menú Principal (`jPanel`)

![Captura de pantalla 2025-03-25 182447](https://github.com/user-attachments/assets/a7ffa3b2-ca4b-47fe-b9e4-8a617f6391eb)

#### 📋 Menús y Configuración del Calendario
* **`jMnuBar`**
  * `mnuOpciones`: Opciones de cierre de sesión y salida.
  * `mnuAbout`: Información del autor.
  * `mnuCalendario`: Despliega el calendario con los entrenamientos asignados por fecha y usuario.

> ⚠️ **Nota de Instalación:** Para que el componente del calendario funcione correctamente, se debe añadir la dependencia al `pom.xml` y configurar el archivo `.jar`.
> 
> **Dependencia Maven:**
> ```xml
> <dependency>
>     <groupId>unknown.binary</groupId>
>     <artifactId>ComponentCalendar-1.0-SNAPSHOT</artifactId>
>     <version>SNAPSHOT</version>
> </dependency>
> ```
> 
> **Instalación del archivo `.jar`:**
> 1. Descargar el `.jar` desde las [Tags del repositorio](https://github.com/nereamo/ProTrainer/tags).
> 2. Ir a la carpeta **Dependencies** de tu proyecto -> Clic derecho (**Add Dependency**) y rellenar los datos.
> 3. Buscar el `ComponentCalendar` en la lista -> Clic derecho (**Manually install artifact**) y seleccionarlo.

#### 👥 Paneles de Control de Usuarios y Entrenamientos
* **`pnlDarkGrey`**
  * `lstUsuarios`: Listado de usuarios asignados al instructor. Al pulsar sobre uno, filtra automáticamente sus entrenamientos.
  * `lblUsuarioIcon`: Icono representativo del perfil.

* **`pnlLightGrey`**
  * `lblWorkouts`: Título de la tabla de entrenamientos.
  * `tblEntrenamientos`: Muestra las rutinas asociadas al usuario seleccionado.

---

#### ➕ Gestión de Entrenamientos y Ejercicios (Formularios)

* **Crear Nuevo Entrenamiento (`btnNuevoWorkout`):**
  
  ![Captura de pantalla 2025-03-25 182454](https://github.com/user-attachments/assets/c06fbf68-acf2-4ae8-9ad2-c2873431486a)

  * `lblTitulo`: Título descriptivo del formulario.
  * `cmbBoxUsuario`: Selector de usuario.
  * `spnrFecha`: Selector de fecha para la rutina.
  * `txtFieldComentario`: Campo para notas o comentarios adicionales.
  * `lblMsg`: Validación visual del estado del formulario.
  * `btnGuardar`: Registra y guarda el entrenamiento.

* **Eliminar Entrenamiento (`btnEliminarWorkout`):**
  
  ![Captura de pantalla 2025-03-25 182459](https://github.com/user-attachments/assets/a1784892-dc63-414b-aa20-3c4732da56fa)

  * `lblTitulo`: Título del formulario.
  * `cmbBoxUsuario`: Selector de usuario.
  * `lstWorkouts`: Lista de entrenamientos del usuario (solo permite eliminar si no tienen ejercicios asignados).
  * `lblMsg`: Muestra errores de validación.
  * `btnEliminar`: Ejecuta el borrado.

* **Crear Nuevo Ejercicio (`btnNuevoEjercicio`):**
  
  ![Captura de pantalla 2025-03-25 182504](https://github.com/user-attachments/assets/40a4e488-cdbf-4bde-b813-e212c4b49b44)

  * `lblTitulo`: Título del formulario.
  * `txtFieldNombre`: Campo para el nombre del ejercicio.
  * `txtFieldDescripcion`: Campo para añadir la descripción.
  * `lblMsg`: Estado y validación del formulario.
  * `btnGuardar`: Guarda el nuevo ejercicio.

* **Eliminar Ejercicio (`btnEliminarEjercicio`):**
  
  ![Captura de pantalla 2025-03-25 182508](https://github.com/user-attachments/assets/8137364d-7384-4ccc-9494-5bcc50daf97d)

  * `lblTitulo`: Título del formulario.
  * `lstEjercicios`: Selección del ejercicio a eliminar (restringido si está asignado a un entrenamiento).
  * `lblMsg`: Mensajes de error o estado.
  * `btnEliminar`: Procesa el borrado.

* **Editar Ejercicio (`btnEditarEjercicio`):**
  
  ![Captura de pantalla 2025-03-25 182513](https://github.com/user-attachments/assets/22cea573-5868-4779-b3a5-4c91b4499d95)

  * `lblTitulo`: Título del formulario.
  * `cmbBoxEjercicios`: Selector del ejercicio a modificar.
  * `txtFieldNombre`: Nuevo nombre para el ejercicio.
  * `txtFieldDescripcion`: Nueva descripción.
  * `lblMsg`: Mensajes de validación de campos.
  * `btnGuardar`: Aplica y guarda los cambios.

* **Asignar Ejercicios a Entrenamientos (`btnAsignarEjercicios`):**
  
  ![Captura de pantalla 2025-03-25 182519](https://github.com/user-attachments/assets/f92cdfa7-bd0f-490c-a5ec-a5ff3b670843)

  * `lblTitulo`: Título del formulario.
  * `cmbBoxUsuarios`: Selector de usuario.
  * `cmbBoxWorkouts`: Muestra los entrenamientos asignados al usuario.
  * `lstEjercicios`: Lista interactiva para seleccionar el ejercicio a vincular.
  * `lblMsg`: Mensajes de estado y validación.
  * `btnGuardar`: Guarda la relación creada.

---

## ✨ Usabilidad: Affordance y Feedback Visual

* **🎯 Affordance:**
  * `jDialogLogin`: Opción visible para alternar la visibilidad de la contraseña.
  * `jTables` / `jList` / `jButtons`: Al pasar el cursor sobre filas o botones, el puntero cambia a forma de mano y los elementos se resaltan mediante bordes o cambios de tono para denotar interactividad.

* **💬 Feedback Visual:**
  * Todos los elementos interactivos cuentan con *tooltips* informativos que muestran una breve descripción al situar el cursor encima.
  * Se han eliminado los `JOptionPane` emergentes molestos: toda la retroalimentación de éxito o error se gestiona mediante etiquetas dinámicas (`lblMsg`) integradas directamente en cada formulario para guiar al usuario paso a paso.

---

## 🚀 Arquitectura y Otros Aspectos Técnicos

1. **Patrón DAO:** Creación de clases especializadas para desacoplar por completo la lógica de negocio y la persistencia de datos según cada objeto.
2. **Clase `EventosMouse`:** Centraliza toda la lógica relacionada con el comportamiento visual, efectos de interacción (*hover*) y feedback de listas, tablas y botones.
3. **Optimización de UX:** Notificaciones guiadas en pantalla mediante `jLabel` en lugar de ventanas emergentes invasivas.

---

## 🔄 Evolución respecto a *TrainMaster*

* Elementos gráficos (botones, tablas y listas) rediseñados y con realce visual avanzado.
* Renovación completa de la paleta de colores de la aplicación.
* Sustitución de botones de texto plano por componentes mucho más visuales.
* Implementación del gestor de diseño **`MigLayout`** para garantizar la escalabilidad y adaptabilidad de la interfaz.

---

## 🖼️ Galería: Comparativa Antes y Después

> **🔴 ANTES (TrainMaster):**
    
![Captura de pantalla 2025-03-21 144835](https://github.com/user-attachments/assets/563b9f88-0ccc-4bcc-810e-88012ef5d85a)

![Captura de pantalla 2025-03-21 144844](https://github.com/user-attachments/assets/21e9f7ae-a992-4d92-bf6a-a35f227e279e)

![Captura de pantalla 2025-03-21 144858](https://github.com/user-attachments/assets/058cfa11-f7bb-480a-b76b-54c3fda09d14)

---

> **🟢 DESPUÉS (ProTrainer):**

![Captura de pantalla 2025-03-25 182332](https://github.com/user-attachments/assets/cd2e2916-b8ec-4159-99a0-2adf9e64ac32)

![Captura de pantalla 2025-03-24 141601](https://github.com/user-attachments/assets/2120e0d4-fe59-487d-b5d3-51b1f86d6e6f)

![Captura de pantalla 2025-03-25 182447](https://github.com/user-attachments/assets/7cc00c5e-9e56-405d-ba6e-36b724a01e9f)
