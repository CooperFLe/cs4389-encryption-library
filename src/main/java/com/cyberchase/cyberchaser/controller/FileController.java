package com.cyberchase.cyberchaser.controller;

import com.cyberchase.cyberchaser.service.AWSService;

import java.io.IOException;

import com.cyberchase.cyberchaser.model.AWSPayload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;

@RestController
public class FileController {
    @RequestMapping(value = "/aws", method = RequestMethod.POST)
    public ResponseEntity<String> aesEncryption(@RequestBody AWSPayload payload) {
        AWSService.uploadString(payload.getFileName(), payload.getContents().getBytes());
        return new ResponseEntity<String>(String.format("File %s uploaded to https://s3.us-east-2.amazonaws.com/cyberchaser.com/%s", payload.getFileName(), payload.getFileName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/aws/file/{documentName}", method = RequestMethod.POST)
    public ResponseEntity<String> aesEncryption(
        @ApiParam(name = "file", value = "Select the file to Upload", required = true)
        @RequestPart("file") MultipartFile file,
        @PathVariable("documentName") String name)
    {
        try {
            AWSService.uploadFile(name, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(String.format("File %s uploaded to https://s3.us-east-2.amazonaws.com/cyberchaser.com/%s", name, name), HttpStatus.OK);
    }
}
