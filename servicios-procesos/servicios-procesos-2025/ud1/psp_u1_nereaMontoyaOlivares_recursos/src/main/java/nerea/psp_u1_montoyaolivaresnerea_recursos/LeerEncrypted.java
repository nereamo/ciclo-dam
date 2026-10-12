
package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
* @author Nerea
*/

public class LeerEncrypted {
    public LeerEncrypted() {
    }

    public static void main(String[] args) {
        try {
            File file = new File("encrypted.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException var4) {
            IOException e = var4;
            e.printStackTrace();
        }

    }
}
