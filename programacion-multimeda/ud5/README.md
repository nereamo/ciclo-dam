# 🚀 pmdm_plantilla2_firebase

Plantilla base para proyectos en Flutter con integración de **Firebase Realtime Database**.

---

## 🛠️ Getting Started

### 1. Configuración en Firebase
1. Crear un nuevo proyecto en la consola de Firebase.
2. Habilitar y estructurar tus nodos de datos en **Realtime Database**.
3. Asegurarte de configurar las reglas de seguridad (*read/write* a `true` para pruebas o según corresponda).
4. Copiar la URL base de tu base de datos.

### 2. Validación con Postman
1. Copia la URL de Firebase y añade la extensión `.json` al final (por ejemplo: `https://tu-proyecto-default-rtdb.europe-west1.firebasedatabase.app/products.json`).
2. Realiza una petición **GET** en Postman.
3. Copia el resultado en formato JSON obtenido.

### 3. Generación de Modelos con QuickType
1. Pega el JSON obtenido en Postman en la herramienta [QuickType](https://quicktype.io/):  
   ![image](https://github.com/user-attachments/assets/ae94af61-d4e4-4881-b0ec-0ce40faa13c1)
   
2. Asegúrate de tener seleccionadas las siguientes opciones de configuración:  
   ![image](https://github.com/user-attachments/assets/e9afd6dc-e7e1-416f-b162-2e1e78183550)  
   ![image](https://github.com/user-attachments/assets/4fad38d1-7920-4d50-af03-f5629ac9ed89)

3. Crea la clase objeto resultante dentro de la carpeta `models` de tu proyecto:  
   ![image](https://github.com/user-attachments/assets/4504e99c-5f9b-4ac8-af53-f057bbb0822b)
