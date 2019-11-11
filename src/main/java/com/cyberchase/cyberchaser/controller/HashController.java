package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.BcryptService;
import com.cyberchase.cyberchaser.service.MD5Service;
import com.cyberchase.cyberchaser.service.Sha512;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {

    @RequestMapping("/bcrypt/{value}")
    public ResponseEntity<String> bcryptHash(@PathVariable("value") String value) {
        return ResponseEntity.ok(BcryptService.hash(value));
    }

    @RequestMapping("/bcrypt/verify/{value}/{hash}")
    public ResponseEntity<Boolean> bcryptVerify(@PathVariable("value") String value, @PathVariable("hash") String hash) {
        return ResponseEntity.ok(BcryptService.checkHash(value, hash));
    }

    @RequestMapping("/md5/{value}")
    public String md5Hash(@PathVariable("value") String value) {
        return MD5Service.hash(value);
    }

    @RequestMapping("/md5/verify/{value}/{hash}")
    public boolean md5Check(@PathVariable("value") String value, @PathVariable("hash") String hash) {
        return MD5Service.checkHash(value, hash);
    }

    @RequestMapping("/Sha512/{hash}/{salt}")
    public String sha512Hash(@PathVariable("salt") String salt, @PathVariable("hash") String value) {
        return Sha512.getHash(value, salt.getBytes());
    }

    @RequestMapping("/Sha512/verify/{value}/{salt}/{hash}")
    public boolean sha512Check(@PathVariable("value") String value, @PathVariable("salt") String salt, @PathVariable("hash") String hash) {
        return Sha512.checkHash(value, salt, hash);
    }
    
}