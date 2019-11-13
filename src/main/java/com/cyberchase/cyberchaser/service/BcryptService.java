package com.cyberchase.cyberchaser.service;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptService {

    public static String hash(byte[] secret) {
        return BCrypt.hashpw(secret, BCrypt.gensalt());
    }

    public static boolean checkHash(String secret, String hash) {
        return BCrypt.checkpw(secret, hash);
    }
}