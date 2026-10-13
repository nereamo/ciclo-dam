# 🚀 Examen Final - Detalles de Lanzamientos (Flutter)

Pantalla de detalles de lanzamientos espaciales desarrollada en **Flutter** para la evaluación final, con soporte para gestión de favoritos mediante almacenamiento local.

## ✨ Funcionalidades Principales

* **Visualización detallada:** Muestra información clave del lanzamiento:
  * Nombre del lanzamiento.
  * Descripción / Misión.
  * Agencia espacial (*LSP*).
  * Fecha de lanzamiento (*NET*).
  * Estado actual de la misión.
* **Gestión inteligente de imágenes:** Carga la imagen oficial mediante URL y, si no está disponible o no es válida, muestra una imagen local por defecto (`assets/no-image.jpg`).
* **Sistema de Favoritos:** Permite marcar o desmarcar lanzamientos como favoritos pulsando el icono de la estrella en la barra superior.
* **Persistencia de datos:** Los favoritos se guardan localmente utilizando `SharedPreferences`, asegurando que el estado se mantenga al reiniciar la aplicación.

---

## 💻 Detalles Técnicos y Componentes

* **Tipo de Widget:** `StatefulWidget` (`LanzamientoDetailsScreen`) para gestionar dinámicamente el estado de los favoritos en la interfaz.
* **Paquete de Persistencia:** `shared_preferences: ^...` para guardar el estado de favorito asociado al ID único de cada lanzamiento (`favorite_{id}`).
* **Estructura de la Interfaz:** 
  * `Scaffold` con un `AppBar` personalizado en tonos oscuros y botón de acción.
  * `SingleChildScrollView` y `Column` para asegurar que la información se despliegue de forma fluida y responsiva.
  * Métodos auxiliares privados (`_buildImagePreview` y `_buildInfoRow`) para modularizar y limpiar el código de la interfaz.
