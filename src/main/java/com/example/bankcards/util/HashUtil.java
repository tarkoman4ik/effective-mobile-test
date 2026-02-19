package com.example.bankcards.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.HexFormat;

@Component
public class HashUtil {

    public String hash(String cardNumber){
        if (cardNumber==null){
            return null;
        }
        String digitsOnly = cardNumber.replaceAll("\\D", "");
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(digitsOnly.getBytes());
            return HexFormat.of().formatHex(hash);
        }
        catch (Exception e){
            throw new RuntimeException("Алгоритм хэширования SHA-256 не доступен", e);
        }
    }

}
