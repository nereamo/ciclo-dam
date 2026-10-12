package montoya.mediapollingcomponent;

import java.util.EventObject;
import java.util.List;
import montoya.mediapollingcomponent.apiclient.Media;

/**
 *
 * @author Nerea
 */
public class MediaEvent extends EventObject{
    
    private List<Media> mediaList;
    private String date;
    
    public MediaEvent(Object source, List<Media> mediaList,String date){
        super(source);
        this.mediaList = mediaList;
        this.date = date;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    } 
}
