
package nerea.psp_u5_servidor_montoyaolivaresnerea.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.crypto.Cipher;

/**
* @author Nerea
*/

public class RSA_Asimetric {
    public RSA_Asimetric() {
    }

    public static KeyPair randomGenerate(int len) {
        KeyPair keys = null;

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(len);
            keys = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException var3) {
            System.err.println("Generador no disponible.");
        }

        return keys;
    }

    public static byte[] decryptData(byte[] dataEncrypted, PrivateKey priv) {
        byte[] Data = null;

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
            cipher.init(2, priv);
            Data = cipher.doFinal(dataEncrypted);
        } catch (Exception var4) {
            Exception ex = var4;
            System.err.println("Error xifrant: " + String.valueOf(ex));
        }

        return Data;
    }
}

