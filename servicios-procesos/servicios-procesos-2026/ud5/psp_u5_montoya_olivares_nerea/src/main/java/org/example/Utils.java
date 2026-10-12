package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

public class Utils {

    //Configuración cifrado
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String AES_ECB = "AES/ECB/PKCS5Padding";
    private static final String RSA_ECB = "RSA/ECB/PKCS1Padding";

    //Array de 16 bytes, divide mensajes en bloques.
    public static final IvParameterSpec iv = new IvParameterSpec(new byte[16]);

    //============ GENERADOR CLAVES ============

    //Genera clave aleatoria para AES -> ECB y CBC
    public static SecretKey keygenKeyGeneration(int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(keySize);
                sKey = kgen.generateKey(); //Genera secret key

            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;
    }

    //Genera claves aleatorias para RSA y FIRMA
    public static KeyPair randomGenerate(int len) {
        KeyPair keys = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator .getInstance("RSA");
            keyGen.initialize(len);
            keys = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Generador no disponible.");
        }
        return keys;
    }

    //============ AES ECB ============

    //Cifra datos con AES modo ECB (no usa IV)
    public static byte[] encryptDataECB(SecretKey sKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, sKey); //Inicia cifrado
            encryptedData = cipher.doFinal(data); //Cifra los datos
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return encryptedData;
    }

    //Descifra datos con AES modo ECB (no usa IV)
    public static byte[] decryptDataECB(SecretKey sKey, byte[] dataEncrypted) {
        byte[] Data = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB);
            cipher.init(Cipher.DECRYPT_MODE, sKey); //Inicia descifrado
            Data = cipher.doFinal(dataEncrypted); //Descifra los datos
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return Data;
    }

    //============ AES CBC ============

    //Cifra datos con AES modo CBC (usa IV)
    public static byte[] encryptDataCBC(SecretKey sKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, sKey, iv); //Inicia cifrado
            encryptedData = cipher.doFinal(data); //Cifra los datos
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return encryptedData;
    }

    //Descifra datos con AES modo CBC (usa IV)
    public static byte[] decryptDataCBC(SecretKey sKey, byte[] dataEncrypted) {
        byte[] Data = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC); //Inicia descifrado
            cipher.init(Cipher.DECRYPT_MODE, sKey, iv); //Descifra los datos
            Data = cipher.doFinal(dataEncrypted);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return Data;
    }

    //============ RSA ============

    //Cifra datos con RSA
    public static byte[] encryptDataRSA(byte[] data, PublicKey pub) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, pub); //Inicia cifrado + clave publica
            encryptedData = cipher.doFinal(data); //Cifra los datos
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return encryptedData;
    }

    //Descifra datos con RSA
    public static byte[] decryptDataRSA(byte[] dataEncrypted, PrivateKey priv) {
        byte[] Data = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ECB);
            cipher.init(Cipher.DECRYPT_MODE, priv); //Inicia descifrado + clave privada
            Data = cipher.doFinal(dataEncrypted); //Descifra los datos
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return Data;
    }

    //============ FIRMA ============

    //Firma el mensaje
    public static byte[] signData(byte[] data, PrivateKey priv) {
        byte[] signature = null;
        try {
            Signature signer = Signature.getInstance("SHA256withRSA"); //Crea hash
            signer.initSign(priv); //Inicia con clave privada
            signer.update(data); //Datos originales
            signature = signer.sign(); //Genera Firma y hash
        } catch (Exception ex) {
            System.err.println("Error al firmar: " + ex);
        }
        return signature;
    }

    //Valida la firma el mensaje
    public static boolean validateSignature(byte[] data, byte[] signature, PublicKey pub) {
        boolean isValid = false;
        try {
            Signature verifier = Signature.getInstance("SHA256withRSA");
            verifier.initVerify(pub); //Inicia con clave publica
            verifier.update(data); //Datos originales recibidos
            isValid = verifier.verify(signature); //Verifica si los datos coinciden con la firma
        } catch (Exception ex) {
            System.err.println("Error al validar firma: " + ex);
        }
        return isValid;
    }
}
