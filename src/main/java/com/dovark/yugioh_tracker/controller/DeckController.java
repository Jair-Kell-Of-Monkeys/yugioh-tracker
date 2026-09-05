package com.dovark.yugioh_tracker.controller;

import com.dovark.yugioh_tracker.dto.DeckCardResponse;
import com.dovark.yugioh_tracker.dto.DeckResponse;
import com.dovark.yugioh_tracker.model.Card;
import com.dovark.yugioh_tracker.model.Deck;
import com.dovark.yugioh_tracker.model.DeckCard;
import com.dovark.yugioh_tracker.repository.CardRepository;
import com.dovark.yugioh_tracker.repository.DeckCardRepository;
import com.dovark.yugioh_tracker.repository.DeckRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final DeckCardRepository deckCardRepository;

    public DeckController(DeckRepository deckRepository,
                          CardRepository cardRepository,
                          DeckCardRepository deckCardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
        this.deckCardRepository = deckCardRepository;
    }

    @GetMapping
    public List<DeckResponse> getAllDecks() {
        return deckRepository.findAll().stream()
                .map(DeckResponse::fromEntity)
                .toList();
    }

    @PostMapping
    public DeckResponse createDeck(@RequestBody Deck deck) {
        Deck saved = deckRepository.save(deck);
        return DeckResponse.fromEntity(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckResponse> getDeckById(@PathVariable Long id) {
        return deckRepository.findById(id)
                .map(DeckResponse::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<DeckCardResponse>> getDeckCards(@PathVariable Long id) {
        if (!deckRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<DeckCardResponse> cards = deckCardRepository.findByDeckId(id).stream()
                .map(DeckCardResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/{id}/cards")
    public ResponseEntity<DeckCardResponse> addCardToDeck(@PathVariable Long id,
                                                          @RequestBody AddCardRequest request) {
        Deck deck = deckRepository.findById(id).orElse(null);
        Card card = cardRepository.findById(request.cardId()).orElse(null);

        if (deck == null || card == null) {
            return ResponseEntity.notFound().build();
        }

        DeckCard deckCard = new DeckCard(deck, card, request.quantity(), request.section());
        DeckCard saved = deckCardRepository.save(deckCard);
        return ResponseEntity.ok(DeckCardResponse.fromEntity(saved));
    }
}