
package nerea.psp_u5_servidor_montoyaolivaresnerea.crypto;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

/**
* @author Nerea
*/
public class Hash {
    public Hash() {
    }

    public static boolean compareHash(SecretKey hashclient, SecretKey hashserver) {
        if ((new String(hashclient.getEncoded(), StandardCharsets.UTF_8)).equals(new String(hashserver.getEncoded(), StandardCharsets.UTF_8))) {
            System.out.println("CORRECT hash, the message has not been modified");
            return true;
        } else {
            System.out.println("FALSE hash, the message has been modified");
            return false;
        }
    }
}
