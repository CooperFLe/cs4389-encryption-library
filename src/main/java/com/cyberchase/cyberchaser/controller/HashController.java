package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.BcryptService;

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
        return ResponseEntity.ok(BcryptService.checkHash(value, null, hash));
    }
    
}