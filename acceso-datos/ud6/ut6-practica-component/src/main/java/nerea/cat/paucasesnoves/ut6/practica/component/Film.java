package nerea.cat.paucasesnoves.ut6.practica.component;

import java.util.Objects;

/**
 * Clase Film, contiene atributos, constructor, getts and setts, equals, hashcode y toString;
 *
 * @author Nerea Montoya
 */

public class Film {
    
    private int id;
    private String title;
    private String description;
    private String year;
    private int length;
    
    public Film(){
        
    }
    
    public Film(int id,String title,String description,String year,int length){
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.length = length;
    }

    public int getFilmId(){
        return id;
    }
    
    public void setFilmId(int id){
        this.id = id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getYear(){
        return year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    
    public int getLength(){
        return length;
    }
    
    public void setLength(int length){
        this.length = length;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.year);
        hash = 29 * hash + this.length;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Film other = (Film) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.year, other.year);
    }

    @Override
    public String toString() {
        return "Film{" + "filmId=" + id + ", title=" + title + ", description=" + description + ", year=" + year + ", length=" + length + '}';
    }

}
