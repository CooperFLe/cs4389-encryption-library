package com.cyberchase.cyberchaser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("aws.properties")
public class AWSConfig {
    @Value("${aws_access_key_id}")
    private String accessKey;
    @Value("${aws_secret_access_key}")
    private String secretKey;
    @Value("${region}")
    private String region;

    public String getAccessKey() {
        return this.accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}