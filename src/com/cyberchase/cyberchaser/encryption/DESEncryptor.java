package com.cyberchase.cyberchaser.encryption;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public abstract class DESEncryptor implements Encryptor{

    private static byte[] akey;
    private static SecretKeySpec privatekey;

    public static void setKey(String bkey) {
        MessageDigest foo = null;
        try {
            akey = bkey.getBytes("UTF-8");
            foo = MessageDigest.getInstance("SHA-1");
            akey = foo.digest(akey);
            akey = Arrays.copyOf(akey, 8);
            privatekey = new SecretKeySpec(akey, "DES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encryptFile(String stringencrypt, String priv) {
        try{
            setKey(priv);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privatekey);

            return Base64.getEncoder().encodeToString(cipher.doFinal(stringencrypt.getBytes("UTF-8")));
        }
        catch(Exception e){
            System.out.println("Encrypting err: " + e.toString());
        }
        return null;
    }

    public static String decryptFile(String stringdecrypt, String priv) {
        try{
            setKey(priv);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, privatekey);

            return new String(cipher.doFinal(Base64.getDecoder().decode(stringdecrypt)));
        }
        catch(Exception e){
            System.out.println("Decrypting err: " + e.toString());
        }
        return null;
    }
}
