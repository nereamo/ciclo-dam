
package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
* @author Nerea
*/

public class EjecutarIndex {
    public EjecutarIndex() {
    }

    public static void main(String[] args) {
        try {
            File htmlFile = new File("index.html");
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("El archivo index.html se ha abierto en el navegador");
            } else {
                System.out.println("El archivo index.html no se ha encontrado");
            }
        } catch (IOException var2) {
            IOException e = var2;
            System.out.println("No se pudo abrir el archivo" + e.getMessage());
            e.printStackTrace();
        }

    }
}
