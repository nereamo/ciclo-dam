
package nerea.muestra.ORM_EntityManager_UD4;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Aspirant;
import nerea.muestra.ORM_EntityManager_UD4.JPQL.DTO_JPQL;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Illes;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Localitat;
import nerea.muestra.Exception.AppException;
/**
 * @author Nerea
 */

public class Main {

    public static void main(String[] args) {
        
        
        
        try {
            
            //Conexion con DTO
            DTO_JPQL dto = new DTO_JPQL();
            String idIlla = "072";

            //Devuelve el nombre de la isla según su ID;
            Illes illa = dto.getIlla(idIlla);
            System.out.println("\nNombre de la isla: " + illa);
            
            //Devuelve las localidades de una isla
            List<Localitat> localidades = dto.getLocalitatIlla(idIlla);
            for(Localitat loc : localidades){
                System.out.println("Localidades de la isla seleccionada: " + loc);
            }
            System.out.println(" ");
            


            
            //Conexion con  por class Persistencia
            Persistencia per = new Persistencia();

            String nif = "12007493F";
            String apellido = "Romero Flores";
            String idLocalitat = "070150003"; 
            
            //Devuelve las datos de un aspirante
            System.out.println("\nDatos del aspirante: ");
            Aspirant aspirante = per.getAspirant(nif);
            System.out.println(aspirante);
            
            //Devuelve las datos de un aspirante
            System.out.println("\nDatos del aspirante: ");
            Aspirant aspirante2 = per.getAspirantSingl(nif);
            System.out.println(aspirante2);
            
            //Devuelve las datos de un aspirante
            System.out.println("\nDatos del aspirante: ");
            Aspirant aspirante3 = per.getAspirantName(nif);
            System.out.println(aspirante3);
            
            
            //Opcion 1 = Modificar los datos de un aspirante
            System.out.println("\nModificación del apellido del aspirante con el nif seleccionado: ");
            Aspirant aspirante1 = per.modificarAspirante(nif, apellido);
            System.out.println(aspirante1);
           
            
            //Opcion 2 = Actualizar los datos del aspirante en la base de datos.
            System.out.println("\nActualizar los datos del aspirante seleccionado en la base de datos: ");
            Aspirant aspirante4 = per.getAspirant(nif);
            aspirante2.setLlinatges("Perez Pascual");
            per.actualitzaAspirant(aspirante2);
            System.out.println(aspirante4);
            
            
//            //Devuelve la localidad según su ID
//            System.out.println("\nNombre de la Localidad: ");
//            Localitat localitat = per.getLocalitat(idLocalitat);
//            System.out.println(localitat);
            
            //Insertar un objeto en la base de dato, como ejemplo insertamos una isla nueva
            System.out.println("\nInsertar nuevo objeto: ");
            per.insertObject("33", "Pascua");
            System.out.println("Nueva isla insertada: " + dto.getIllaList());
            
            //Actualizar los datos de un objeto de la base de datos.
            System.out.println("\nActualizar los datos del objeto (isla) seleccionado en la base de datos: ");
            per.updateObject("33", "Florencia");
            System.out.println("Nueva isla insertada: " + dto.getIllaList());
            
            
            //Eliminar un objeto de la base de datos.
            System.out.println("\nEliminar un objeto (isla) seleccionado en la base de datos: ");
            per.deleteObject("33");
            System.out.println("Islas en la base de datos: " + dto.getIllaList());
            
//            
//            //Opcion 1 = Crear un Aspirante con parametros en el método sin necesidad de retornar objeto.
//            System.out.println("\nNuevo Aspirante: ");
//            
//            String nifA = "00000000";
//            String nombre = "Ana";
//            String apellidos = "Pim Pum";
//            String calle = "Calle 1";
//            String cp = "0000";
//            
//            Localitat localitat2 = per.getLocalitat(idLocalitat);
//
//            Aspirant newAspirant = per.creaAspiranteParametros1(nifA, nombre, apellidos, calle,cp,localitat2);
//            System.out.println(newAspirant);
//            
//            
//            //Crear nueva Preferencia con parametros en el metodo
//            String nif3 = "00000000";
//            int ordre = 40;
//            String idCos = "0590";
//            String idEspecialitat = "001";
//            String idCentre = "07002831";
// 
//            Preferencia p = per.creaPreferencia(nif3, ordre, idCos, idEspecialitat, idCentre);
//            System.out.println("Preferencia creada: " + p);
            
            
            per.cerrarFactory();
            
            

            
        } catch (AppException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

}
