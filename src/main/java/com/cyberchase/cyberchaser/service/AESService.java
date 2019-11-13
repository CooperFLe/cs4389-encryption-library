package com.cyberchase.cyberchaser.service;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import org.springframework.web.multipart.MultipartFile;

public abstract class AESService {

    private static byte[] akey;
    private static SecretKeySpec privatekey;

    public static void setKey(String bkey) {
        MessageDigest foo = null;
        try {
            akey = bkey.getBytes("UTF-8");
            foo = MessageDigest.getInstance("SHA-1");
            akey = foo.digest(akey);
            akey = Arrays.copyOf(akey, 16);
            privatekey = new SecretKeySpec(akey, "AES");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encryptFile(MultipartFile file, String priv)
    {
        try {
            setKey(priv);
            Cipher ciph = Cipher.getInstance("AES/ECB/PKCS5Padding");
            ciph.init(Cipher.ENCRYPT_MODE, privatekey);
            return Base64.getEncoder().encodeToString(ciph.doFinal(file.getBytes()));
        }
        catch (Exception e) {
            System.out.println("Encrypting err: " + e.toString());
        }
        return null;
    }

    public static String decryptFile(MultipartFile file, String priv)
    {
        try {
            setKey(priv);
            Cipher ciph = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            ciph.init(Cipher.DECRYPT_MODE, privatekey);
            return new String(ciph.doFinal(Base64.getDecoder().decode(file.getBytes())));
        }
        catch (Exception e) {
            System.out.println("Decrypting err: " + e.toString());
        }
        return null;
    }

    public static String encryptString(String plaintext, String priv)
    {
        try {
            setKey(priv);
            Cipher ciph = Cipher.getInstance("AES/ECB/PKCS5Padding");
            ciph.init(Cipher.ENCRYPT_MODE, privatekey);
            return Base64.getEncoder().encodeToString(ciph.doFinal(plaintext.getBytes()));
        }
        catch (Exception e) {
            System.out.println("Encrypting err: " + e.toString());
        }
        return null;
    }

    public static String decryptString(String stringdecrypt, String priv)
    {
        try {
            setKey(priv);
            Cipher ciph = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            ciph.init(Cipher.DECRYPT_MODE, privatekey);
            return new String(ciph.doFinal(Base64.getDecoder().decode(stringdecrypt)));
        }
        catch (Exception e) {
            System.out.println("Decrypting err: " + e.toString());
        }
        return null;
    }
}