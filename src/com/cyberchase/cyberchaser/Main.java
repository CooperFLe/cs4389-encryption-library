package com.cyberchase.cyberchaser;

import com.cyberchase.cyberchaser.encryption.AESEncryptor;

import java.lang.String;

public class Main {

    public static void main(String[] args) {
        final String secretKey = "ssshhhhhhhhhhh!!!!";

        String originalString = "howtodoinjava.com";
        String encryptedString = AESEncryptor.encryptFile(originalString, secretKey);
        String decryptedString = AESEncryptor.decryptFile(encryptedString, secretKey);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
