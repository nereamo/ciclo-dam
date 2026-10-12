package org.example;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Base64;

import static org.example.Utils.*;

public class Main {

    private static String mensaje = "Este es un mensaje secreto que nadie debe leer"; //Mensaje
    private static SecretKey secretKey = keygenKeyGeneration(128); //Clave AES
    private static KeyPair pairKey = randomGenerate(2048); //Par de claves RSA y Firma
    private static SecretKey aesKey = keygenKeyGeneration(256); //Clave RSA + AES

    public static void main(String[] args) {

        System.out.println("\n========== MENSAJE ORIGINAL ==========");
        System.out.println(mensaje);

        System.out.println("\n========== AES ECB ==========");
        operationECB();

        System.out.println("\n========== AES CBC ==========");
        operationCBC();

        System.out.println("\n========== RSA ==========");
        operationRSA();

        System.out.println("\n========== RSA + AES ==========");
        operationRSA_AES();

        System.out.println("\n========== FIRMA ==========");
        operationFirma();
    }

    //Operación de cifrado y descifrado EAS-ECB
    public static void operationECB(){
        //Cifrado
        byte[] cifrado = encryptDataECB(secretKey, mensaje.getBytes());
        System.out.println("AES ECB -> Cifrado (Base64): " + Base64.getEncoder().encodeToString(cifrado));

        //Descifrar
        byte[] descifrado = decryptDataECB(secretKey, cifrado);
        System.out.println("AES ECB -> Descifrado: " + new String(descifrado));
    }

    //Operación de cifrado y descifrado EAS-CBC
    public static void operationCBC(){
        //Cifrado
        byte[] cifrado = encryptDataCBC(secretKey, mensaje.getBytes());
        System.out.println("AES CBC -> Cifrado (Base64): " + Base64.getEncoder().encodeToString(cifrado));

        //Descifrar
        byte[] descifrado = decryptDataCBC(secretKey, cifrado);
        System.out.println("AES CBC -> Descifrado: " + new String(descifrado));
    }

    //Operación de cifrado y descifrado RSA
    public static void operationRSA(){
        //Cifrar
        byte [] cifrado = encryptDataRSA(mensaje.getBytes(), pairKey.getPublic());
        System.out.println("RSA -> Cifrado (Base64): " + Base64.getEncoder().encodeToString(cifrado));

        //Descifrar
        cifrado = decryptDataRSA(cifrado, pairKey.getPrivate());
        System.out.println("RSA -> Descifrado: " + new String(cifrado, StandardCharsets.UTF_8));
    }

    //Operación de cifrado y descifrado RSA + AES
    public static void operationRSA_AES(){
        //Cifrar mensaje con AES
        byte [] mensajeCifrado = encryptDataCBC(aesKey, mensaje.getBytes());
        System.out.println("RSA + AES -> Mensaje cifrado con AES (Base64): " + Base64.getEncoder().encodeToString(mensajeCifrado));

        //Cifrar clave AES con RSA
        byte [] claveCifrada = encryptDataRSA(aesKey.getEncoded(), pairKey.getPublic());
        System.out.println("RSA + AES -> Clave AES cifrada (Base64): " + Base64.getEncoder().encodeToString(claveCifrada));

        //Descifrar clave AES con RSA
        byte[] claveDescifradaBytes = decryptDataRSA(claveCifrada, pairKey.getPrivate());

        //Reconstruir clave AES
        SecretKey claveDescifrada = new javax.crypto.spec.SecretKeySpec(claveDescifradaBytes, "AES");

        //Descifrar texto
        byte[] mensajeDescifrado = decryptDataCBC(claveDescifrada, mensajeCifrado);
        System.out.println("RSA -> Mensaje Descifrado: " + new String(mensajeDescifrado, StandardCharsets.UTF_8));
    }

    //Operación de firma de los datos y validación de la firma
    public static void operationFirma(){
        //Firma datos
        byte[] firma = signData(mensaje.getBytes(), pairKey.getPrivate());
        System.out.println("Firma (Base64): " + Base64.getEncoder().encodeToString(firma));

        //Valida firma
        boolean isValid = validateSignature(mensaje.getBytes(), firma, pairKey.getPublic());
        System.out.println("¿La firma es válida?: " + isValid);
    }
}
