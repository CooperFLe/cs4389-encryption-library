package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.AESService;
import com.cyberchase.cyberchaser.service.DESService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {

    @RequestMapping("/aes/enc/{key}/{value}")
    public String aesEncryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return AESService.encryptFile(value, key);
    }

    @RequestMapping("/aes/dec/{key}/{value}")
    public String aesDecryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return AESService.decryptFile(value, key);
    }

    @RequestMapping("/des/enc/{key}/{value}")
    public String desEncryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return DESService.encryptFile(value, key);
    }

    @RequestMapping("/des/dec/{key}/{value}")
    public String desDecryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return DESService.decryptFile(value, key);
    }
    
}
