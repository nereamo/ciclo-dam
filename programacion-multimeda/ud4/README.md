# 📱 PMDM - Unidad 04: Aplicación Flutter con QR y Mapas

Aplicación móvil desarrollada en **Flutter** que combina el escaneo de códigos QR (con gestión de directorios locales), la persistencia de elementos y la visualización geográfica interactiva mediante mapas.

---

## ✨ Características y Funcionalidades Principales

### 🏠 Pantalla Principal (HomeScreen)
* **Barra superior (`AppBar`):** 
  * Título identificativo de la aplicación.
  * Botón de acción rápida para **eliminar todos** los elementos almacenados.
* **Navegación inferior:** 
  * Botón principal para activar el **escaneo de códigos QR**.
  * Accesos directos a los directorios de **Mapas** y **Direcciones URL**.
* **Gestión de elementos escaneados:**
  * Al escanear un código, se almacena automáticamente en su lista/directorio correspondiente (`geo` o `http`).
  * **Interacción táctil:** Pulsa sobre cualquier elemento para visualizar su contenido en detalle, o deslízalo (*swipe*) hacia un lado para eliminarlo de forma individual.

### 📷 Lector de Códigos QR
* Capacidad para interpretar y diferenciar dinámicamente dos tipos de códigos:
  * **Tipo `http`:** Abre la dirección web correspondiente.
  * **Tipo `geo`:** Abre la vista integrada de mapas con las coordenadas geográficas obtenidas.

### 🗺️ Visualizador de Mapas (Google Maps)
Al abrir un elemento de tipo `geo`, se despliega una pantalla de mapas que incluye:
* **Barra superior (`AppBar`):**
  * Botón de retroceso para volver al menú.
  * Título descriptivo.
  * Opción para **centrar el puntero** en las coordenadas de la ubicación.
  * **Capa de tráfico en tiempo real** (Funcionalidad avanzada añadida como mejora de la práctica).
* **Controles inferiores y flotantes:**
  * Botones dedicados para **aumentar y disminuir el zoom**.
  * Botón flotante (*Floating Action Button*) para **cambiar el tipo de vista del mapa** (por ejemplo: satélite, normal, híbrido).

---

## 🚀 Tecnologías y Paquetes Utilizados
* **Framework:** Flutter / Dart
* **Gestión de Estado y UI:** Widgets de tipo `StatefulWidget` y componentes nativos de Material Design.
* **Librerías principales habituales en el proyecto:**
  * `mobile_scanner` / `flutter_barcode_scanner` (para la lectura de códigos QR).
  * `google_maps_flutter` (para la integración y control de los mapas).
  * `url_launcher` (para la apertura de URLs externas).
