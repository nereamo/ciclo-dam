package nerea.blobpollingcomponent;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.Timer;

//Partes de código adaptadas de IA y recomendaciones de compañeros.
/**
 *
 * @author Nerea
 */
public class BlobPollingComponent extends JPanel implements Serializable, ActionListener {

    private String connectionString;
    private String containerName;
    private boolean running = false;
    private int pollingInterval = 5;
    private Timer timer;
    private List<BlobEventListener> listeners = new ArrayList<>();    //List de listeners
    private List<BlobItem> blobList = new ArrayList<>();
    private boolean isFirstPoll = true;

    public BlobPollingComponent() {
        setLayout(new BorderLayout());
        timer = new Timer(pollingInterval * 1000, e -> pollingServer());
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running; //Actualizar el estado de ejecución

        if (running) {
            if (!timer.isRunning()) { // Inicia solo si no está ya en ejecución.
                timer.start();
            }
        } else {
            if (timer.isRunning()) { // Detén solo si está ejecutándose.
                timer.stop();
            }
        }
    }

    public int getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(int pollingInterval) {
        this.pollingInterval = pollingInterval;
        if (timer != null) {
            timer.setDelay(pollingInterval * 1000);
        }
    }
    
    private void pollingServer() {
        //Autentificar la conexion con azure

        if (connectionString == null || containerName == null) {
            System.err.println("connectionString o containerName no configurados.");
            return;
        }

        try {
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

            List<BlobItem> currentBlobs = new ArrayList<>();
            for (BlobItem blobItem : containerClient.listBlobs()) {
                currentBlobs.add(blobItem);
            }

            //Determinar si hay archivos nuevos
            List<BlobItem> newBlob = currentBlobs.stream()
                    .filter(file -> blobList.stream().noneMatch(existing -> existing.getName().equals(file.getName())))
                    .collect(Collectors.toList());

            //Determinar su hay archivos eliminados
            List<BlobItem> removedBlob = blobList.stream()
                    .filter(file -> currentBlobs.stream().noneMatch(current -> current.getName().equals(file.getName())))
                    .collect(Collectors.toList());
 
            blobList = currentBlobs; //Actualizar la lista local de archivos

            if (!isFirstPoll) {
                notifyListeners(newBlob, removedBlob);
            }

            isFirstPoll = false;

        } catch (Exception e) {
            System.err.println("Error al consultar el servidor.");
        }
    }
    
    private void notifyListeners(List<BlobItem> newBlobs, List<BlobItem> removedBlobs) {
        for (BlobEventListener listener : listeners) {
            if (!newBlobs.isEmpty()) {
                listener.onBlobNotification("New blobs: \n" + messageNotifyListeners(newBlobs));
            }
            if (!removedBlobs.isEmpty()) {
                listener.onBlobNotification("Blobs removed: \n" + messageNotifyListeners(removedBlobs));
            }
        }
    }

    private String messageNotifyListeners(List<BlobItem> blobs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BlobItem blob : blobs) {
            stringBuilder.append("Name: ").append(blob.getName()).append(", \n");
            stringBuilder.append("Size: ").append(blob.getProperties().getContentLength()).append(", \n");
            stringBuilder.append("Latest Modified: ").append(blob.getProperties().getLastModified());
        }
        return stringBuilder.toString();
    }

    public void addNewBlobListener(BlobEventListener listener) {
        listeners.add(listener);
    }

    public void removeNewBlobListener(BlobEventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       pollingServer();
    }
    
}
