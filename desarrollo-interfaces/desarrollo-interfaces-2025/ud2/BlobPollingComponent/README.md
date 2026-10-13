# ☁️ BlobPollingComponent

Componente Java diseñado para la monitorización periódica de contenedores de **Azure Blob Storage**. Al iniciarse, consulta en intervalos configurables el contenedor asignado para detectar la adición o eliminación de archivos, notificando los cambios e información de cada fichero.

---

## ⚙️ Propiedades del Componente

| Propiedad | Descripción |
| :--- | :--- |
| **Connection String** | Cadena de conexión con la dirección e identificación de Azure. |
| **Container Name** | Nombre del contenedor donde se ubican los archivos a monitorizar. |

---

## 🚀 Guía de Ejecución

1. Asignar un valor para el intervalo de consulta (*polling*).
2. Pulsar el botón **ON** para iniciar la monitorización.

---

## 💻 Integración en el `JFrame` de Prueba

### 1. Registrar el Listener de Notificaciones (Constructor)

Añade este bloque dentro del constructor de tu `JFrame` para capturar los eventos y mostrar los mensajes en pantalla:

    blobPollingComponent1.addNewBlobListener(new BlobEventListener() {
        @Override
        public void onBlobNotification(String message) {
            jTextAreaBlobInfo.setText(message);
        } 
    });

Asigna el siguiente código al evento de un JToggleButton para alternar la ejecución del componente: 

    try {
            if (jToggleButtonOnOff.isSelected()) {
                blobPollingComponent1.setPollingInterval((int)jSpinnerInterval.getValue());
                blobPollingComponent1.setRunning(true);
                jToggleButtonOnOff.setText("OFF");  // Show "Off" when activated
                jLabelStatus.setText("Polling activated");
                
        
            } else {
                blobPollingComponent1.setRunning(false);
                jToggleButtonOnOff.setText("ON");   // Show "On" when deactivated
                jLabelStatus.setText("Polling deactivated");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jLabelStatus.setText("Error connecting to Azure.");
        }
