# ProTrainer

## Diseño:

### ProTrainer (jFrame - Main):

![Captura de pantalla 2025-03-25 182332](https://github.com/user-attachments/assets/d057d0ea-3818-47b0-8b7c-2e4e58aeac31)

> jMnuBar

- mnuOpciones: Opciones de Logout(cerrar sesión usuario) y Exit(cerrar aplicación).
- mnuAbout: contiene el About con la información del autor.

> pnlMain

- lblLogo: Logo de al aplicación.
- btnAcceso: Al pulsar se abrirá 'DialogLogin' para ingresar el email y la contraseña e iniciar sesión.
- lblUrl: Icono que nos reenvia a la página web.



### DialogLogin:

![Captura de pantalla 2025-03-24 141601](https://github.com/user-attachments/assets/c72671bc-86bd-49fe-b5b4-f580f6ed72e0)

- txtFieldEmail: Permite escribir el email.
- fldPassword: Permite escribir la contraseña.
- jChkMostrarContraseña: Permite ver la contraseña.
- lblMsg: Muestra mensaje si no se ha escrito bien el email o la contraseña.
- btnLogin: Evento que se accionará al ser pulsado.


### Panel Menú (jPanel):

![Captura de pantalla 2025-03-25 182447](https://github.com/user-attachments/assets/a7ffa3b2-ca4b-47fe-b9e4-8a617f6391eb)

> jMnuBar

- mnuOpciones: Opciones de Logout(cerrar sesión usuario) y Exit(cerrar aplicación).
- mnuAbout: contiene el About con la información del autor.
- mnuCalendario: Muestra un calendario con los entrenamientos asignado a una fecha y usuario.
    
> :warning: **Nota**: Para que el componente del calendario funcione, se debe añadir la dependencia al Pom.xml y el .jar.
    
> Dependencia
    
        <dependency>
            <groupId>unknown.binary</groupId>
            <artifactId>ComponentCalendar-1.0-SNAPSHOT</artifactId>
            <version>SNAPSHOT</version>
        </dependency>
        
> Archivo .jar

Descargar el .jar: https://github.com/nereamo/ProTrainer/tags
    
Añadir .jar a proyecto: 
1. Carpeta Dependencies --> Click Derecho (Add Dependency) y rellenar los campos con la información de la dependencia.
2. Buscar en la lista de dependencias el ComponentCalendar --> Click derecho(Manually install artifact) y buscar el componente.

> pnlDarkGrey

- lstUsuarios: Lista de los usuarios asignados al instructor, al pulsar sobre un usuario, mostrará los entrenamientos asociados.
- lblUsuarioIcon: Icono de usuario.

> pnlLightGrey

- lblWorkouts: Label con el título de la tabla.
- tblEntrenamientos: Muestra los entrenamientos que tiene asociados un usuario.
- btnNuevoWorkout: Permite crear un nuevo entrenamiento asignandolo a un usuario.
  
![Captura de pantalla 2025-03-25 182454](https://github.com/user-attachments/assets/c06fbf68-acf2-4ae8-9ad2-c2873431486a)


      - lblTitulo -> Titulo del formulario.
      - cmbBoxUsuario -> Permite seleccionar un usuario.
      - spnrFecha -> Permite seleccionar una fecha.
      - txtFieldComentario -> Permite añadir un comentario.
      - lblMsg -> Muestra un mensaje de error si alguno de los campos no es correcto.
      - btnGuardar -> Acciona el evento.
  
- btnEliminarWorkout: Permite eliminar un entrenamiento que no tenga ejercicios asignados.
  
![Captura de pantalla 2025-03-25 182459](https://github.com/user-attachments/assets/a1784892-dc63-414b-aa20-3c4732da56fa)

      - lblTitulo -> Titulo del formulario.
      - cmbBoxUsuario -> Permite seleccionar un usuario.
      - lstWorkouts -> Muestra la lista de entrenamientos asignados al usuario.
      - lblMsg -> Muestra un mensaje de error si alguno de los campos no es correcto.
      - btnEliminar -> Acciona el evento.


- lblEjercicios: Label con el título de la tabla.
- tblEjercicios: Muestra los ejercicios que tiene asociados un workout.
- btnNuevoEjercicio: Permite crear un nuevo ejercicio.

![Captura de pantalla 2025-03-25 182504](https://github.com/user-attachments/assets/40a4e488-cdbf-4bde-b813-e212c4b49b44)

      - lblTitulo -> Titulo del formulario.
      - txtFieldNombre -> Permite Añadir un nombre al ejercicio.
      - txtFieldDescripcion -> Permite Añadir una descripción al ejercicio.
      - lblMsg -> Muestra un mensaje de error si alguno de los campos no es correcto.
      - btnGuardar -> Acciona el evento.

- btnEliminarEjercicio: Permite eliminar un ejercicios que no esté asignando a un entrenamiento.
  
![Captura de pantalla 2025-03-25 182508](https://github.com/user-attachments/assets/8137364d-7384-4ccc-9494-5bcc50daf97d)

      - lblTitulo -> Titulo del formulario.
      - lstEjercicios -> Lista que permite seleccionar un ejercicio.
      - lblMsg -> Muestra un mensaje de error si alguno de los campos no es correcto.
      - btnEliminar -> Acciona el evento.
  
- btnEditarEjercicio: Permite cambiar el nombre y la descripción de un ejercicio.
  
![Captura de pantalla 2025-03-25 182513](https://github.com/user-attachments/assets/22cea573-5868-4779-b3a5-4c91b4499d95)


      - lblTitulo -> Titulo del formulario.
      - cmbBoxEjercicios -> Permite seleccionar el ejercicio que se va a modificar.
      - txtFieldNombre -> Permite escribir un nuevo nombre para el ejercicio.
      - txtFieldDescripcion -> Permite escribir una nueva descripción para el ejercicio.
      - lblMsg -> Muestra un mensaje de error si alguno de los campos no es correcto.
      - btnGuardar -> Acciona el evento.
  
- btnAsignarEjercicios: Permite asignar ejercicios a un entrenamiento.
  
![Captura de pantalla 2025-03-25 182519](https://github.com/user-attachments/assets/f92cdfa7-bd0f-490c-a5ec-a5ff3b670843)

      - lblTitulo -> Titulo del formulario.
      - cmbBoxUsuarios -> Permite seleccionar un usuario.
      - cmbBoxWorkouts -> Se mostrarán los entrenamientos que tiene asignado el usuario.
      - lstEjercicios -> Lista que muestra y permite seleccionar que ejercicio sera asignado.
      - lblMsg -> Muestra un mensaje de error si alguno de los campos no es correcto.
      - btnGuardar -> Acciona el evento.



## Affordance
- jDialogLogin -> Se ha añadido la opción de poder ver la contraseña.
  
- jTables -> Al pasar el cursor sobre las filas se muestran resaltadas además del cursor en forma de mano.

- jButtons -> Al pasar el cursor sobre el botón se muestra en forma de mano y es resaltado por un borde.

- jList -> Al pasar el cursor sobre las filas se muestran resaltadas además del cursor en forma de mano.


## Feedback:

Se ha añadido feedback visual a los elementos con los que el usuario debe interactuar para un mayor entendimiento y usabilidad.

Ahora al pasar el ratón sobre un botón éste es destacado, en la listas y tablas las filas son resaltadas y al hacer click sobre un elemento es resaltado con otro color, cada uno de los elementos tiene un 'tooltip' que mustra una breve descripción del elemento sobre el que está el cursor.

En cada formulario hay un lblMsg mostrando información d elos pasos a realizar.
Al completar el formulario, si la opción es correcta mostrá un mensaje informando que la acción es correcta, de lo contrario mostrá un mensaje de error.

## Otros:

1. Creación de las clases DAO: Se han creado las vclases DAO para separa la lógica dependiendo del objeto.
2. Creación de la clase 'EventosMouse' para unir la lógica que hace referéncia al aspecto visual y feedback de los botones, listas y tablas.
3. Eliminación de mensajes JOPtionPane inecesários, ahora los mensages de 'selecciona un usuario' o similares, se muestran en en jLabel, evitando así la carga de ventanas abiertas pero informando en todo momento de los pasos a realizar antes de cualquier cambio.

## Cambios respect al anterior proyecto TrainMaster:

        -Elementos como botones, tablas y listas se han resaltado.
        -Cambio en el color de la aplicación.
        -Cambio de botones con texto por botónes mas visuales.
        -Creación de clases para separar la lógica de elementos visuales e interacción con la base de datos.
        -Se ha implementado el MigLayout para poder ampliar la aplicación.


## Imágenes del antes y después:

> Antes:
    
![Captura de pantalla 2025-03-21 144835](https://github.com/user-attachments/assets/563b9f88-0ccc-4bcc-810e-88012ef5d85a)

![Captura de pantalla 2025-03-21 144844](https://github.com/user-attachments/assets/21e9f7ae-a992-4d92-bf6a-a35f227e279e)

![Captura de pantalla 2025-03-21 144858](https://github.com/user-attachments/assets/058cfa11-f7bb-480a-b76b-54c3fda09d14)


> Después:

![Captura de pantalla 2025-03-25 182332](https://github.com/user-attachments/assets/cd2e2916-b8ec-4159-99a0-2adf9e64ac32)

![Captura de pantalla 2025-03-24 141601](https://github.com/user-attachments/assets/2120e0d4-fe59-487d-b5d3-51b1f86d6e6f)

![Captura de pantalla 2025-03-25 182447](https://github.com/user-attachments/assets/7cc00c5e-9e56-405d-ba6e-36b724a01e9f)






