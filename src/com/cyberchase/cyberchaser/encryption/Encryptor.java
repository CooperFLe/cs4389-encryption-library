package com.cyberchase.cyberchaser.encryption;

import java.io.File;

public interface Encryptor {
    File encryptFile(String fileName);
    File decryptFile(String fileName);
}
