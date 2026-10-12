
package cat.paucasesnovescifp.spaddd.jdbc.dades;

import cat.paucasesnovescifp.spaddd.jdbc.auxiliars.JDBCException;
import java.util.Objects;

/**
* @author Nerea
*/

public class Editor {
    
    int identificador;
    String nom;
    String email;
    
    public Editor(String nom, String email){
     
        this.nom = nom;
        this.email = email;
    }
    
    public Editor(){
        
    }
    
    public void setIdentificador(int identificador) throws JDBCException{
        
        if(identificador  == 0 || identificador <= 0){  //Si l'ID es igual a 0 o menor que 0, saltará l'excepció
            throw new JDBCException("L'identificador no pot ser negatiu o null");
        }
        this.identificador = identificador;
    }
    
    public int getIdentificador(){
        
        return identificador;
    }
    
    public void setNom(String nom) throws JDBCException{
        
        if(nom == null || nom.trim().isEmpty() || nom.length() > 50){   //Si el nom es igual a null, compost per espais en blanc o te mes de 50 carácters saltará l'excepció
            throw new JDBCException("El nom no pot ser null, contenir mes de 50 caracters ni estar en blanc");
        }
        this.nom = nom;
    }
    
    public String getNom(){
        
        return nom;
    }
    
    public void setEmail(String email) throws JDBCException{
        
        if (email != null) {    //Si el email es diferent a null, no pot estar compost per espais en blanc o tenir mes de 75 carácters.
            if (email.trim().isEmpty() || email.length() > 75) {
                throw new JDBCException("L'email no pot tenir mes de 75 caracters ni estar en blanc");
            }
        }
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }

    @Override   
    public int hashCode() { //Valor asignado al nombre
        return Objects.hash(nom);
    }

    @Override //objeto igual a otro
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
        final Editor other = (Editor) obj;
        return Objects.equals(this.nom, other.nom);
    }   
    
    @Override
    public String toString() {
        return  "NOM: " + nom 
                + ",  EMAIL: " + email;
    }

}
