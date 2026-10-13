
package nerea.muestra.Ficheros_Path_Streams_UD2.JSON;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import nerea.muestra.Ficheros_Path_Streams_UD2.JSON.POJO.Curs;
import nerea.muestra.Ficheros_Path_Streams_UD2.JSON.POJO.Modul;

/**
 * @author Nerea
 */

public class MainJson {

    public static void main(String[] args) {
        
        try{
            
            Json json = new Json();
            
            //Main
            String rutaLectura = "C:\\Users\\Nerea\\Downloads\\fitxers json\\curs.json";
            String rutaLeerArrayModul = "C:\\Users\\Nerea\\Downloads\\fitxers json\\arrayModuls.json";
            
            //Lee JSON de POJO 
            Curs curso = json.leerGenérico(rutaLectura, Curs.class);
            System.out.println(curso);
            
            //Lee Array JSON
            Modul[] modulArray = json.leerGenérico(rutaLeerArrayModul, Modul[].class);
            for(Modul m : modulArray){
                System.out.println(m);
            }

            //Lee ArrayList JSON usando TypeReference
            List<Modul> modulList = json.leerGenérico2(rutaLeerArrayModul, new TypeReference<List<Modul>>() {});
            System.out.println(modulList);

//            //Lee JSON desde URL
//            Autor autor = json.leerDesdeUrl("http://daw.paucasesnovescifp.cat:8080/Biblioteca/autors/1", Autor.class);
//            System.out.println(autor);


            //Main
            String rutaEscritura = "C:\\Users\\Nerea\\Downloads\\fitxers json\\JSON.json";
            String rutaEscribirArrayModul = "C:\\Users\\Nerea\\Downloads\\fitxers json\\arraysJson.json";
            
            //Escribir JSON de POJO
            json.escribeJson(rutaEscritura, curso);
            
            //Escribe JSON como Array[]
            json.escribeJson(rutaEscribirArrayModul, modulArray);
            
            //Escribe JSON como ArrayList
            json.escribeJson(rutaEscribirArrayModul, modulList);

            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
