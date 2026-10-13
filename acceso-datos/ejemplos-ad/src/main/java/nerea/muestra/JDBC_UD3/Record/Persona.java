package nerea.muestra.JDBC_UD3.Record;


/**
 *
 * @author Nerea
 */

//Forma de simplificar una clase sin implementación
//Se implementa la class con constructor.
public record Persona(String nombre, int edad) {
    
    public Persona{
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacío o ser null");
        }
        
        if(edad < 0){
            throw new IllegalArgumentException("La edad no puede ser menor que 0");
        }
    }

}
