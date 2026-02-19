package com.example.bankcards.util;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class Masking {

    @Named("mask")
    public String maskCardNumber(String cardNumber){
        if (cardNumber==null || cardNumber.isBlank()){
            return "";
        }
        String digitsOnly = cardNumber.replaceAll("\\D", "");
        String lastFourSymbols = cardNumber.substring(digitsOnly.length()-4);
        return "**** **** **** "+lastFourSymbols;
    }
}
