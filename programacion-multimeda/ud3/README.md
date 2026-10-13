# 🎮 PMDM - Unidad 03: Aplicación de Juegos en Flutter

Aplicación móvil desarrollada en **Flutter** para la exploración de videojuegos, destacando los lanzamientos recientes y los títulos más populares mediante componentes visuales avanzados.

---

## ✨ Características y Estructura Principal

### 🏠 Pantalla Principal (HomeScreen)
La pantalla de inicio integra dos widgets principales con desplazamiento dinámico:
* **`GameSwiper`:** Carrusel interactivo que muestra los juegos lanzados en el año **2025**.
* **`GameSlider`:** Componente deslizante horizontal que muestra los videojuegos **más populares**.

### 📄 Pantalla de Detalles del Juego
Al pulsar sobre cualquier juego (tanto desde el `GameSwiper` como desde el `GameSlider`), se abre una vista detallada que incluye la siguiente información:
* **Barra superior (`AppBar`):** Incorpora la imagen representativa del juego.
* **Título principal:** Nombre del videojuego.
* **Género:** Categoría a la que pertenece (por ejemplo: *RPG*, *Shooter*, *Acción*, etc.). Si un juego no dispone de esta información, muestra por defecto el mensaje *"Género no disponible"*.
* **Póster oficial:** Imagen de portada del título.
* **Descripción detallada:** Sinopsis o información del juego. Si carece de texto descriptivo, muestra el mensaje *"Descripción no disponible"*.
* **Galería de capturas (*Screenshots*):** Una sección final con una serie de imágenes pertenecientes al juego.

> 💡 **Gestión de imágenes:** Si algún juego no cuenta con una imagen válida que mostrar en cualquiera de sus secciones, la aplicación cargará automáticamente una **imagen genérica por defecto** para evitar errores visuales.

---

## 🚀 Tecnologías y Componentes
* **Framework:** Flutter / Dart
* **Gestión de UI:** Widgets avanzados para carruseles y listas deslizantes (`Swiper` / `Slider`).
* **Manejo de estados y nulos:** Validación robusta de datos opcionales (géneros, descripciones e imágenes alternativas).
