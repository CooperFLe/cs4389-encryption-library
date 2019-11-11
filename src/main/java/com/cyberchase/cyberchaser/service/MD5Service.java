package com.cyberchase.cyberchaser.service;

import java.security.*;

public abstract class MD5Service {

    public static boolean checkHash(String plaintext, String hash) {
        return hash.equals(hash(plaintext));
    }

    public static String hash(String stringencrypt) {
        String newpword = null;
        try {
            MessageDigest foo = MessageDigest.getInstance("MD5");
            foo.update(stringencrypt.getBytes());
            byte[] bytes = foo.digest();
            StringBuilder bar = new StringBuilder();
            for(int i = 0; i < bytes.length; i++) {
                bar.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            newpword = bar.toString();
            return newpword;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(newpword);
        return null;
    }

}