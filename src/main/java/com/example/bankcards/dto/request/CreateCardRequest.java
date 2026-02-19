package com.example.bankcards.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {
    @NotBlank
    @Pattern(regexp = "[0-9]{16}",message = "Номер карты должен содержать 16 цифр")
    private String cardNumber;

    @NotNull
    @Future(message = "Срок действия должен быть в будущем")
    private YearMonth expiryDate;

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    @NotNull
    private Long userId;
}
