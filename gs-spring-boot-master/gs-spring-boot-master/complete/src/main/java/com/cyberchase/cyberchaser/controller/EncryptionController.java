package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.AESEncryptor;
import com.cyberchase.cyberchaser.service.DESEncryptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {

    @RequestMapping("/aes/enc/{key}/{value}")
    public String aesEncryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return AESEncryptor.encryptFile(value, key);
    }

    @RequestMapping("/aes/dec/{key}/{value}")
    public String aesDecryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return AESEncryptor.decryptFile(value, key);
    }

    @RequestMapping("/des/enc/{key}/{value}")
    public String desEncryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return DESEncryptor.encryptFile(value, key);
    }

    @RequestMapping("/des/dec/{key}/{value}")
    public String desDecryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        System.out.println(DESEncryptor.decryptFile("ggOeT3T5ekEvkTlePeOj3g==", "superSecretKey"));
        return DESEncryptor.decryptFile(value, key);
    }
    
}
