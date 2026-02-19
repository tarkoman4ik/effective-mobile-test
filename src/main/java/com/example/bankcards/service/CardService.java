package com.example.bankcards.service;

import com.example.bankcards.dto.request.CreateCardRequest;
import com.example.bankcards.dto.response.CardResponse;
import com.example.bankcards.entity.enums.CardStatus;

public interface CardService {
    CardResponse createCard(CreateCardRequest request);
    CardResponse updateCardStatus(Long cardId, CardStatus status);
}
