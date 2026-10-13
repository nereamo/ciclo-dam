# BlobPollingComponent

Componente que al arrancar, cada intervalo refrescará y comparará si se han añadido/eliminado archivos del contenedor asignado, mostrando la información del archivo.

Properties: 

Connection String (añadir direccion azure).
Container Name (añadir nombre del contenedor donde estan ubicados los archivos).

Ejecución: 
Asignar un valor de intervalo y pulsar botón ON.

En el JFrame de prueba debes añadir esto:

Añadir al constructor:

    blobPollingComponent1.addNewBlobListener(new BlobEventListener(){
                @Override
                public void onBlobNotification(String message) {
                    jTextAreaBlobInfo.setText(message);
                }    
            });  

Añadir ToggleButton y el siguiente código: 

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
