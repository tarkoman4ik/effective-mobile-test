package com.example.bankcards.service.impl;

import com.example.bankcards.dto.request.CreateCardRequest;
import com.example.bankcards.dto.response.CardResponse;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.enums.CardStatus;
import com.example.bankcards.mapper.CardMapper;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.CardService;
import com.example.bankcards.util.HashUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final HashUtil hashUtil;

    @Transactional
    @Override
    public CardResponse createCard(CreateCardRequest request){
        Card card = cardMapper.toEntity(request);
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        card.setUser(user);
        String hash = hashUtil.hash(request.getCardNumber());
        card.setCardNumberHash(hash);
        Card savedCard = cardRepository.save(card);
        return cardMapper.toResponse(savedCard);
    }

    @Transactional
    @Override
    public CardResponse updateCardStatus(Long cardId, CardStatus status){
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Карта не найдена"));
        card.setStatus(status);
        Card savedCard = cardRepository.save(card);
        return cardMapper.toResponse(savedCard);
    }
}
