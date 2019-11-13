package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.BcryptService;
import com.cyberchase.cyberchaser.service.MD5Service;
import com.cyberchase.cyberchaser.service.Sha512Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.ApiParam;

@RestController
public class HashController {

    @RequestMapping(value = "/bcrypt/{value}", method = RequestMethod.POST)
    public ResponseEntity<String> bcryptHash(@PathVariable("value") String value) {
        return ResponseEntity.ok(BcryptService.hash(value));
    }

    @RequestMapping(value = "/bcrypt/verify/{value}/{hash}", method = RequestMethod.POST)
    public ResponseEntity<Boolean> bcryptVerify(@PathVariable("value") String value, @PathVariable("hash") String hash) {
        return ResponseEntity.ok(BcryptService.checkHash(value, hash));
    }

    @RequestMapping(value = "/md5/{value}", method = RequestMethod.POST)
    public String md5Hash(@PathVariable("value") String value) {
        return MD5Service.hash(value);
    }

    @RequestMapping(value = "/md5/verify/{value}/{hash}", method = RequestMethod.POST)
    public boolean md5Check(@PathVariable("value") String value, @PathVariable("hash") String hash) {
        return MD5Service.checkHash(value, hash);
    }

    @RequestMapping(value = "/md5/file", method = RequestMethod.POST)
    public String md5HashFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file) {
        return MD5Service.hashFile(file);
    }

    @RequestMapping(value = "/Sha512/{hash}/{salt}", method = RequestMethod.POST)
    public String sha512Hash(@PathVariable("salt") String salt, @PathVariable("hash") String value) {
        return Sha512Service.getHash(value, salt.getBytes());
    }

    @RequestMapping(value = "/Sha512/verify/{value}/{salt}/{hash}", method = RequestMethod.POST)
    public boolean sha512Check(@PathVariable("value") String value, @PathVariable("salt") String salt, @PathVariable("hash") String hash) {
        return Sha512Service.checkHash(value, salt, hash);
    }
    
}