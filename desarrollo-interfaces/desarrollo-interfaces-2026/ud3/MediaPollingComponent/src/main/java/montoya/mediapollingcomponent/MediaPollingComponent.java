package montoya.mediapollingcomponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import montoya.mediapollingcomponent.apiclient.ApiClient;
import montoya.mediapollingcomponent.apiclient.Media;

/**
 *
 * @author Nerea
 */
public class MediaPollingComponent extends JPanel implements Serializable, ActionListener {

    private String apiUrl;
    private boolean running;
    private int pollingInterval = 0;
    private String token;
    private String lastChecked;
    private Timer timer;
    private ApiClient client;
    private List<MediaListener> listeners;

    public MediaPollingComponent() {

        this.setLayout(new BorderLayout());

        JLabel label = new JLabel(new ImageIcon(getClass().getResource("/images/orangeIconLogoSmall.png")));
        add(label, BorderLayout.CENTER);

        if(listeners == null){
            listeners = new ArrayList<>();
        }
        
        if(timer == null){
            timer = new Timer (pollingInterval * 1000 , this);
        }
        
        running = false;
    }
    
    //====== Getters and Setters ======
    
    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        
        this.apiUrl = apiUrl;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {

        this.running = running;
        startTimer();
    }

    public int getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(int pollingInterval) {

        this.pollingInterval = pollingInterval;
        startTimer();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        
        this.token = token;
        startTimer();
    }
    
    public String getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(String lastChecked) {
        this.lastChecked = lastChecked;
    }
    
    //====== Metodo que inicia el timer ======
    
    private void startTimer() {

        if (timer == null) {
            timer = new Timer(pollingInterval * 1000, this);
        } else {
            timer.setDelay(pollingInterval * 1000);
        }

        if (running == true && pollingInterval > 0 && token != null && apiUrl != null) {
            if (!timer.isRunning()) {
                timer.start();
            }
        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
    }
    
    //====== Methods Wrapper ======
    
    public String login(String email, String password) throws Exception{
        
        if(client == null){
            client = new ApiClient(apiUrl);
        }
        
        token = client.login(email, password);
        
        return token; 
    }
    
    public String getNickName(int id, String jwt) throws Exception{
        
        if(client == null){
            client = new ApiClient(apiUrl);
        }
        return client.getNickName(id, jwt);
    }
    
    public List<Media> getAllMedia(String jwt) throws Exception{
        
        if(client == null){
            client = new ApiClient(apiUrl);
        }
        return client.getAllMedia(jwt);
    }
    
    public List<Media> getMediaAddedSince(OffsetDateTime from, String jwt) throws Exception{
        if(client == null){
            client = new ApiClient(apiUrl);
        }
        return client.getMediaAddedSince(from, jwt);
    }
    
    public void download(int id, File destFile, String jwt) throws Exception{
        
        if(client == null){
            client = new ApiClient(apiUrl);
        }
        
        client.download(id, destFile, jwt);
    }
    
    public String uploadFileMultipart(File file, String downloadedFromUrl, String jwt) throws Exception{
        
        if(client == null){
            client = new ApiClient(apiUrl);
        }
        
        return client.uploadFileMultipart(file, downloadedFromUrl, jwt);
    }

    //====== Listeners ======

    public void addMediaListener(MediaListener l) {
        listeners.add(l);
    }

    public void removeMediaListener(MediaListener l) {
        listeners.remove(l);
    }
    
    //====== ActionPerformed ======

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!running) {
            return;
        }
        if (token == null || token.trim().isEmpty()) {
            System.out.println("Token not found");
            return;
        }
        if (apiUrl == null || apiUrl.trim().isEmpty()) {
            System.out.println("ApiURL is incorrect or error when entering server");
            return;
        }

        if (client == null) {
            try {
                client = new ApiClient(apiUrl);
            } catch (Exception ex) {
                System.err.println("Error initializing ApiClient: " + ex.getMessage());
                ex.printStackTrace();
                return;
            }
        }

        String dateNow = OffsetDateTime.now().toString();
        
        try {
            List<Media> mediaList;
            
            if(lastChecked == null){
                mediaList = client.getAllMedia(token);
            }else{
                mediaList = client.getMediaAddedSince(lastChecked, token);
            }

            if (!mediaList.isEmpty()) {
                MediaEvent info = new MediaEvent(this, mediaList, dateNow);

                for (MediaListener ml : listeners) {
                    ml.newMediaFound(info);
                }
            }else{
                System.out.println("No new media were found");
            }

        } catch (Exception ex) {
            System.err.println("Error, the requested media could not be obtained: " + ex.getMessage());
            ex.printStackTrace();
        }

        lastChecked = dateNow;
    }
}
