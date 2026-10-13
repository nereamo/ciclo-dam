
package nerea.psp_u5_cliente_montoyaolivaresnerea.crypto;

import java.security.PublicKey;
import javax.crypto.Cipher;

/**
* @author Nerea
*/

//Cifrar datos sensibles (como la clave AES) usando RSA y la clave pública del receptor.
public class RSA_Asimetric {
    public RSA_Asimetric() {
    }

    public static byte[] encryptData(byte[] data, PublicKey pub) {
        byte[] encryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
            //cipher.init(Cipher.ENCRYPT_MODE, pub);
            cipher.init(1, pub);
            encryptedData = cipher.doFinal(data);
        } catch (Exception var4) {
            Exception ex = var4;
            System.err.println("Error xifrant: " + String.valueOf(ex));
        }
        /**
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        */

        return encryptedData;
    }
}
