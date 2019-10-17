package com.cyberchase.cyberchaser;

import com.cyberchase.cyberchaser.encryption.AESEncryptor;
import com.cyberchase.cyberchaser.encryption.DESEncryptor;

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

        originalString = "newteststring";
        encryptedString = DESEncryptor.encryptFile(originalString, secretKey);
        decryptedString = DESEncryptor.decryptFile(encryptedString, secretKey);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
