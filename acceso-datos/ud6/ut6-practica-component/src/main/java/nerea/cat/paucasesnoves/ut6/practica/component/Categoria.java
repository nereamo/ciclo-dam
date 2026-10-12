package nerea.cat.paucasesnoves.ut6.practica.component;

import java.util.Objects;

/**
 * Clase Categoria, contiene atributos, constructor, getts and setts, equals, hashcode y toString;
 *
 * @author Nerea Montoya
 */

public class Categoria {
    
    private int categoryId;
    private String value;
    
    public Categoria(){
        
    }
    
    public Categoria(int id, String value){
        this.categoryId = id;
        this.value = value;
    }
    
    public int getCategoryId(){
        return categoryId;
    }
    
    public void setCategoryId(int id){
        this.categoryId = id;
    }
    
    public String getValue(){
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.categoryId;
        hash = 47 * hash + Objects.hashCode(this.value);
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
        final Categoria other = (Categoria) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return "Categoria{" + "categoriaId=" + categoryId + ", value=" + value + '}';
    }

}
