package com.cyberchase.cyberchaser.controller;

import java.io.IOException;

import com.cyberchase.cyberchaser.service.BcryptService;
import com.cyberchase.cyberchaser.service.MD5Service;
import com.cyberchase.cyberchaser.service.Sha512Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.ApiParam;

@RestController
public class FileHashController {

    @RequestMapping(value = "/bcrypt/file", method = RequestMethod.POST)
    public String bcryptHashFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true) 
            @RequestPart("file") MultipartFile file) {
        try {
            return BcryptService.hash(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/md5/file", method = RequestMethod.POST)
    public String md5HashFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file) {
        return MD5Service.hashFile(file);
    }

    @RequestMapping(value = "/md5/file/{hash}", method = RequestMethod.POST)
    public boolean md5VerifyFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file,
            @PathVariable("hash") String hash) {
        return hash.equals(MD5Service.hashFile(file));
    }

    @RequestMapping(value = "/Sha512/{salt}", method = RequestMethod.POST)
    public String sha512FileHash(
        @ApiParam(name = "file", value = "Select the file to Upload", required = true)
        @RequestPart("file") MultipartFile file,
        @PathVariable("salt") String salt) {
        return Sha512Service.getFileHash(file, salt.getBytes());
    }

    @RequestMapping(value = "/Sha512/verify/file/{value}/{salt}/{hash}", method = RequestMethod.POST)
    public boolean sha512FileCheck(
        @ApiParam(name = "file", value = "Select the file to Upload", required = true)
        @RequestPart("file") MultipartFile file,
        @PathVariable("salt") String salt, @PathVariable("hash") String hash) {
        return Sha512Service.checkFileHash(file, salt, hash);
    }
    
}