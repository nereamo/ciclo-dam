
package nerea.muestra.Ficheros_Path_Streams_UD2.Streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nerea.muestra.Ficheros_Path_Streams_UD2.JSON.POJO.Modul;


/**
* @author Nerea
*/

public class DataStreams {
    
    public void escribirDataStreams(String ruta, int num, double decimal, String txt) {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)))) {
            out.writeInt(num); 
            out.writeDouble(decimal); 
            out.writeUTF(txt); 
            out.flush(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String leerDataStreams(String ruta) {
        String result = "";
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(ruta)))) {
            result = in.readInt() + "\n" + in.readDouble() + "\n" + in.readUTF(); 
        } catch (IOException e) {
            e.printStackTrace();
      
        }
        return result;
    }
    
    
    //Escribe array
    public void writeData(String ruta, List<Modul> modul){
        
        try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)))){
            dos.writeInt(modul.size());
            
            for(Modul m : modul){
                dos.writeUTF(m.getCodi());
                dos.writeUTF(m.getNom());
                dos.writeInt(m.getHores());
            }
            dos.flush();  
        }catch(Exception e){
            e.getMessage();
        }  
    }
    
    //Lee Array
    public List<Modul> readData(String ruta) {
        List<Modul> moduls = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(ruta)))) {

            int numModuls = dis.readInt(); // Llegim el nombre total de mòduls

            for (int i = 0; i < numModuls; i++) {
                moduls.add(new Modul(dis.readUTF(), dis.readUTF(), dis.readInt()));
            }

        } catch (IOException e) {
            System.err.println("Error al llegir dades: " + e.getMessage());
        }

        return moduls;
    }
}
