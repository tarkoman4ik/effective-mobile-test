package com.example.bankcards.mapper;

import com.example.bankcards.dto.request.CreateCardRequest;
import com.example.bankcards.dto.response.CardResponse;
import com.example.bankcards.entity.Card;
import com.example.bankcards.util.Masking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.YearMonth;

@Mapper(componentModel = "spring", uses = Masking.class)
public interface CardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "cardNumberHash", ignore = true)
    @Mapping(target = "expiryDate", source = "expiryDate", qualifiedByName = "yearMonthToLocalDate")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Card toEntity(CreateCardRequest request);


    @Named("yearMonthToLocalDate")
    default LocalDate yearMonthToLocalDate(YearMonth yearMonth) {
        return yearMonth != null ? yearMonth.atEndOfMonth() : null;
    }

    @Mapping(target = "maskedCardNumber", source = "cardNumber", qualifiedByName = "mask")
    @Mapping(target = "expiryDate", source = "expiryDate", qualifiedByName = "localDateToYearMonth")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "userId", source = "user.id")
    CardResponse toResponse(Card card);

    @Named("localDateToYearMonth")
    default YearMonth localDateToYearMonth(LocalDate localDate){
        return localDate != null ? YearMonth.from(localDate) : null;
    }

}
