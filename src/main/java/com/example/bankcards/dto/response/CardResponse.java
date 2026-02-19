package com.example.bankcards.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
public class CardResponse {
    private Long id;
    private String maskedCardNumber;
    private YearMonth expiryDate;
    private String status;
    private BigDecimal balance;
    private Long userId;
}
