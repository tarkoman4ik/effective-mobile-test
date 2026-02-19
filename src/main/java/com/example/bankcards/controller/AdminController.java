package com.example.bankcards.controller;

import com.example.bankcards.dto.request.CreateCardRequest;
import com.example.bankcards.dto.response.CardResponse;
import com.example.bankcards.entity.enums.CardStatus;
import com.example.bankcards.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CardService cardService;

    @PostMapping("/cards")
    public ResponseEntity<CardResponse> createCard(@Valid @RequestBody CreateCardRequest request) {
        CardResponse cardResponse = cardService.createCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardResponse);
    }

    @PutMapping("/cards/{cardId}/status")
    public ResponseEntity<CardResponse> updateCardStatus(@PathVariable Long cardId, @RequestParam CardStatus status){
        return ResponseEntity.ok(cardService.updateCardStatus(cardId,status));
    }
}
