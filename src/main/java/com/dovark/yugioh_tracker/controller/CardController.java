package com.dovark.yugioh_tracker.controller;

import com.dovark.yugioh_tracker.dto.CardResponse;
import com.dovark.yugioh_tracker.model.Card;
import com.dovark.yugioh_tracker.repository.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<CardResponse> getAllCards() {
        return cardRepository.findAll().stream()
                .map(CardResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getCardById(@PathVariable Long id) {
        return cardRepository.findById(id)
                .map(CardResponse::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CardResponse createCard(@RequestBody Card card) {
        Card saved = cardRepository.save(card);
        return CardResponse.fromEntity(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        if (!cardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cardRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}