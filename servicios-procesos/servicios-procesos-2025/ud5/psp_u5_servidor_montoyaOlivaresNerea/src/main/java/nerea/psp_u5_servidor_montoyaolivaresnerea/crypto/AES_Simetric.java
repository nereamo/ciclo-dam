
package nerea.psp_u5_servidor_montoyaolivaresnerea.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
* @author Nerea
*/

public class AES_Simetric {
    public static final byte[] IV_PARAM = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public AES_Simetric() {
    }

    public static byte[] decryptData(SecretKey sKey, byte[] dataEncrypted) {
        byte[] Data = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(2, sKey, iv);
            Data = cipher.doFinal(dataEncrypted);
        } catch (Exception var5) {
            Exception ex = var5;
            System.err.println("Error xifrant les dades: " + String.valueOf(ex));
        }

        return Data;
    }
}
