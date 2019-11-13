package com.cyberchase.cyberchaser.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.web.multipart.MultipartFile;

public class Sha512Service {

    public static String hash(String value) {
        try {
            return getHash(value, getSalt());
        } catch(NoSuchAlgorithmException e) {
            return e.getMessage();
        }
    }

    public static String getFileHash(MultipartFile file, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(file.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean checkFileHash(MultipartFile plaintext, String salt, String hash) {
        return hash.equals(getFileHash(plaintext, salt.getBytes()));
    }

    public static String getHash(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean checkHash(String plaintext, String salt, String hash) {
        return hash.equals(getHash(plaintext, salt.getBytes()));
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}