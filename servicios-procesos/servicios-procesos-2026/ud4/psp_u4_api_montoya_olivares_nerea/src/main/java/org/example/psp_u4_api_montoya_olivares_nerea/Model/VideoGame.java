package org.example.psp_u4_api_montoya_olivares_nerea.Model;

import java.io.Serializable;

/**
 * Clase modelo de videojuegos.
 * Establece las propiedades que debe tener el objeto.
 * Contiene Getters y Setters.
 */
public class VideoGame implements Serializable {

    //Variables
    private int id;
    private String title;
    private String year;
    private String modality;
    private String enterprise;

    public VideoGame() {}

    public VideoGame(int id, String title, String year, String modality, String enterprise) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.modality = modality;
        this.enterprise = enterprise;
    }

    //Getter y Setter de Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Getter y Setter de Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Getter y Setter de Year
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //Getter y Setter de Modality
    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    //Getter y Setter de Enterprise
    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }
}
