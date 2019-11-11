package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.AWSService;

import com.cyberchase.cyberchaser.model.AWSPayload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @RequestMapping(value = "/aws", method = RequestMethod.POST)
    public ResponseEntity<String> aesEncryption(@RequestBody AWSPayload payload) {
        AWSService.uploadString(payload.getFileName(), payload.getContents().getBytes());
        return new ResponseEntity<String>(String.format("File %s uploaded", payload.getFileName()), HttpStatus.OK);
    }
}
