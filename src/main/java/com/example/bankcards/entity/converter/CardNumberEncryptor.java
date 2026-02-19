package com.example.bankcards.entity.converter;

import com.example.bankcards.util.Encryption;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
@Converter
public class CardNumberEncryptor implements AttributeConverter<String,String> {

    private final Encryption encryption;

    public CardNumberEncryptor(Encryption encryption){
        this.encryption = encryption;
    }

    @Override
    public String convertToDatabaseColumn(String CardNumber){
        if (CardNumber==null){
            return null;
        }
        try{
            return encryption.encrypt(CardNumber);
        }
        catch (Exception e){
            throw new RuntimeException("Ошибка шифрования номера карты", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String encryptedCardNumber){
        if (encryptedCardNumber==null){
            return null;
        }
        try {
            return encryption.decrypt(encryptedCardNumber);
        }
        catch (Exception e){
            throw new RuntimeException("Ошибка дешифрования номера карты", e);
        }
    }

}
