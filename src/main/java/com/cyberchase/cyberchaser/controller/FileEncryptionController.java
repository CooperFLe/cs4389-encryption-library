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
public class FileEncryptionController {

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

    @RequestMapping(value = "rsa/enc", method = RequestMethod.POST)
    public RSAResult rsaEncryption(
        @ApiParam(name = "file", value = "Select the file to Upload", required = true)
        @RequestPart("file") MultipartFile file){
        RSAService rsa = new RSAService();
        RSAResult result = new RSAResult();

        result.setEncryptedString(rsa.encryptFile(file));
        result.setPrivateKey(rsa.getPrivateKey());
        result.setPublicKey(rsa.getPublicKey());

        return result;
    }

    @RequestMapping(value = "rsa/dec/{key}", method = RequestMethod.POST)
    public String rsaDecryption(
        @ApiParam(name = "file", value = "Select the file to Upload", required = true)
        @RequestPart("file") MultipartFile file,
        @PathVariable("key") String key){
        RSAService rsa = new RSAService();
        return rsa.decryptFile(file, key);
    }
    
}
