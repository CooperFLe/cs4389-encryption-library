package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.AWSService;

import javax.annotation.PostConstruct;

import com.cyberchase.cyberchaser.config.AWSConfig;
import com.cyberchase.cyberchaser.model.AWSPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    AWSConfig awsConfig;

    @RequestMapping(value = "/aws", method = RequestMethod.POST)
    public ResponseEntity<String> aesEncryption(@RequestBody AWSPayload payload) {
        AWSService.uploadString(payload.getFileName(), payload.getContents().getBytes(), awsConfig.getRegion());
        return new ResponseEntity<String>(String.format("File %s uploaded", payload.getFileName()), HttpStatus.OK);
    }

    @PostConstruct
    public void initialise() {
        System.setProperty("aws.accessKeyId", awsConfig.getAccessKey());
        System.setProperty("aws.secretKey", awsConfig.getSecretKey());
    }
}
