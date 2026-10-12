package nerea.cat.paucasesnoves.ut6.practica.component;

import java.util.Objects;

/**
 * Clase Actor, contiene atributos, constructor, getts and setts, equals, hashcode y toString;
 *
 * @author Nerea Montoya
 */

public class Actor {
    
    private int actorId;
    private String firstName;
    private String lastName;
    
    public Actor(){
        
    }
    
    public Actor(int actorId, String firstName, String lastName){
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;   
    }
    
    public int getActorId(){
        return actorId;
    }
    
    public void setActroId(int actorId){
        this.actorId = actorId;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.actorId;
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
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
        final Actor other = (Actor) obj;
        if (this.actorId != other.actorId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        return Objects.equals(this.lastName, other.lastName);
    }

    

    @Override
    public String toString() {
        return "Actor{" + "actorId=" + actorId + ", FirstName=" + firstName + ", LastName=" + lastName + '}';
    }

}
