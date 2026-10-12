
package nerea.psp_u4_api_api_montoyaolivaresnerea.model;

import java.io.Serializable;

/**
* @author Nerea
*/

public class Obras implements Serializable {
    private int idObra;
    private String titol;
    private String any;
    private String modalitat;
    private String autor;

    public Obras() {
    }

    public Obras(int idObra, String titol, String any, String modalitat, String autor) {
        this.idObra = idObra;
        this.titol = titol;
        this.any = any;
        this.modalitat = modalitat;
        this.autor = autor;
    }

    public int getIdObra() {
        return this.idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getTitol() {
        return this.titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAny() {
        return this.any;
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getModalitat() {
        return this.modalitat;
    }

    public void setModalitat(String modalitat) {
        this.modalitat = modalitat;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
