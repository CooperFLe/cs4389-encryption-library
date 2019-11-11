package com.cyberchase.cyberchaser.service;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RSAService{

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String base64PublicKey;
    private String base64PrivateKey;

    public RSAService() {
        try{
            keyGenerator();
        }
        catch(Exception e){
            //TODO: Handle exception
        }
    }

    public void keyGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public String getPrivateKey(){
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public PrivateKey fixPrivateKey(String originalKey){
        try {
            PrivateKey p = null;
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(originalKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            p = keyFactory.generatePrivate(keySpecPrivate);
            return p;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // public static PublicKey getPublicKey(String base64PublicKey){
    //     PublicKey publicKey = null;
    //     try{
    //         X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
    //         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    //         publicKey = keyFactory.generatePublic(keySpec);
    //         return publicKey;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return publicKey;
    // }

    public String encryptFile(String encryptString){
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(getPublicKey().getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] byteArr = encryptString.getBytes();
            byte[] result = (cipher.doFinal(byteArr));
            return new String(Base64.getEncoder().encode(result));
        }
        catch(Exception e){
            System.out.println("Encrypting err: " + e.toString());
        }

        return null;
    }

    public String decryptFile(String decryptString, String privateKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, fixPrivateKey(privateKey));
            return new String(cipher.doFinal(Base64.getDecoder().decode(decryptString.getBytes())));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // private PrivateKey getDecryptKey(String key) {
    //     PrivateKey privateKey = null;
    //     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
    //     KeyFactory keyFactory = null;
    //     try {
    //         keyFactory = KeyFactory.getInstance("RSA");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     try {
    //         privateKey = keyFactory.generatePrivate(keySpec);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return privateKey;
    // }
}