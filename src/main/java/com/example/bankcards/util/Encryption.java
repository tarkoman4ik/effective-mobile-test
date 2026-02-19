package com.example.bankcards.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class Encryption {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_LENGTH = 12;

    private final SecretKey secretKey;
    private final SecureRandom secureRandom;

    public Encryption(KeyProvider keyProvider) {
        this.secretKey = keyProvider.getSecretKey();
        this.secureRandom = new SecureRandom();
    }

    public String encrypt(String message){
        try{
            byte[] iv = new byte[IV_LENGTH];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

            byte[] cipherText = cipher.doFinal(message.getBytes());

            String encodedIV = Base64.getEncoder().encodeToString(iv);
            String encodedCipherText = Base64.getEncoder().encodeToString(cipherText);
            return encodedIV+":"+encodedCipherText;
        }
        catch (Exception e){
            throw new RuntimeException("Ошибка шифрования", e);
        }
    }

    public String decrypt(String message){
        try{
            String[] parts = message.split(":",2);
            if (parts.length!=2) {
                throw new IllegalArgumentException("Неверный формат зашифрованных данных");
            }
            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] cipherText = Base64.getDecoder().decode(parts[1]);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

            byte[] decodedMessage = cipher.doFinal(cipherText);
            return new String(decodedMessage);
        }
        catch (Exception e){
            throw new RuntimeException("Ошибка дешифрования", e);
        }
    }

}
