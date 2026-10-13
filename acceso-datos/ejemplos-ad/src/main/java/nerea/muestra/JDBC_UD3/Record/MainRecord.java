
package nerea.muestra.JDBC_UD3.Record;

/**
 *
 * @author Nerea
 */
public class MainRecord {

    public static void main(String[] args) {
        Persona p = new Persona("Paco", 23);
        System.out.println("El nombre es " + p.nombre() + " y tiene " + p.edad());
        System.out.println(p);
        
        Persona p1 = new Persona("Sara", 44);
        System.out.println("El nombre es " + p1.nombre() + " y tiene " + p1.edad());
        System.out.println(p1);
        
    }
}
