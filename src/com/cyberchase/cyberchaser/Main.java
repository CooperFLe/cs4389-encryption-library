package com.cyberchase.cyberchaser;

import com.cyberchase.cyberchaser.encryption.AESEncryptor;
import com.cyberchase.cyberchaser.encryption.DESEncryptor;

import java.lang.String;

public class Main {

    public static void main(String[] args) {
        final String privatekey = "superSecretKey";

        String original = "testMessage";
        String encrypted = AESEncryptor.encryptFile(original, privatekey);
        String decrypted = AESEncryptor.decryptFile(encrypted, privatekey);
        
        System.out.println("AES output: Original String, Encrypted String, Decrypted String");
        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);

        original = "newteststring";
        encrypted = DESEncryptor.encryptFile(original, privatekey);
        decrypted = DESEncryptor.decryptFile(encrypted, privatekey);

        System.out.println();
        System.out.println("DES output: Original String, Encrypted String, Decrypted String");
        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
