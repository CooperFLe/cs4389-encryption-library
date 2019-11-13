package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.AESService;
import com.cyberchase.cyberchaser.service.DESService;
import com.cyberchase.cyberchaser.service.RSAService;
import com.cyberchase.cyberchaser.model.RSAResult;

import com.cyberchase.cyberchaser.model.RSAPayload;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.ApiParam;

@RestController
public class EncryptionController {

    @RequestMapping(value = "/aes/enc/{key}/{value}", method = RequestMethod.POST)
    public String aesEncryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return AESService.encryptString(value, key);
    }


    @RequestMapping(value = "/aes/dec/{key}/{value}", method = RequestMethod.POST)
    public String aesDecryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return AESService.decryptString(value, key);
    }

    @RequestMapping(value = "/aes/enc/file/{key}", method = RequestMethod.POST)
    public String aesEncryptFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file,
            @PathVariable("key") String key) {
        return AESService.encryptFile(file, key);
    }

    @RequestMapping(value = "/aes/dec/file/{key}", method = RequestMethod.POST)
    public String aesDecryptFile(
        @ApiParam(name = "file", value = "Select the file to Upload", required = true)
        @RequestPart("file") MultipartFile file,
        @PathVariable("key") String key)
    {
        return AESService.decryptFile(file, key);
    }

    @RequestMapping(value = "/des/enc/{key}/{value}", method = RequestMethod.POST)
    public String desEncryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return DESService.encryptString(value, key);
    }

    @RequestMapping(value = "/des/dec/{key}/{value}", method = RequestMethod.POST)
    public String desDecryption(@PathVariable("key") String key, @PathVariable("value") String value) {
        return DESService.decryptString(value, key);
    }

    @RequestMapping(value = "/des/enc/file/{key}", method = RequestMethod.POST)
    public String desEncryptFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file,
            @PathVariable("key") String key) {
        return DESService.encryptFile(file, key);
    }

    @RequestMapping(value = "/des/dec/file/{key}", method = RequestMethod.POST)
    public String desDecryptFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file,
            @PathVariable("key") String key)
    {
        return DESService.decryptFile(file, key);
    }


    @RequestMapping(value = "rsa/enc/{value}", method = RequestMethod.POST)
    public RSAResult rsaEncryption(@PathVariable("value") String value){
        RSAService rsa = new RSAService();
        RSAResult result = new RSAResult();

        result.setEncryptedString(rsa.encryptFile(value));
        result.setPrivateKey(rsa.getPrivateKey());
        result.setPublicKey(rsa.getPublicKey());

        return result;
    }

    @RequestMapping(value = "rsa/dec", method = RequestMethod.POST)
    public String rsaDecryption(@RequestBody RSAPayload payload){
        RSAService rsa = new RSAService();
        return rsa.decryptFile(payload.getValue(), payload.getKey());
    }
    
}
