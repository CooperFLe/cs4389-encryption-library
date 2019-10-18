package com.cyberchase.cyberchaser;

import com.cyberchase.cyberchaser.encryption.AESEncryptor;

import java.lang.String;

public class Main {

    public static void main(String[] args) {

        final String secretKey = "verySecurePrivateKey";
        String original = "testString";
        String encrypted = AESEncryptor.encryptFile(original, secretKey);
        String decrypted = AESEncryptor.decryptFile(encrypted, secretKey);
        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
