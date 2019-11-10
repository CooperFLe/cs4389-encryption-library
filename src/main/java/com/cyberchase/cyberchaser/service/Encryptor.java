package com.cyberchase.cyberchaser.service;

import java.io.File;

public interface Encryptor {
    File encryptFile(String fileName);
    File decryptFile(String fileName);
}
