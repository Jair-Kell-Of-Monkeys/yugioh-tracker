package com.dovark.yugioh_tracker.controller;

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
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck) {
        return deckRepository.save(deck);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable Long id) {
        return deckRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<DeckCard>> getDeckCards(@PathVariable Long id) {
        if (!deckRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deckCardRepository.findByDeckId(id));
    }

    @PostMapping("/{id}/cards")
    public ResponseEntity<DeckCard> addCardToDeck(@PathVariable Long id,
                                                  @RequestBody AddCardRequest request) {
        Deck deck = deckRepository.findById(id).orElse(null);
        Card card = cardRepository.findById(request.cardId()).orElse(null);

        if (deck == null || card == null) {
            return ResponseEntity.notFound().build();
        }

        DeckCard deckCard = new DeckCard(deck, card, request.quantity(), request.section());
        DeckCard saved = deckCardRepository.save(deckCard);
        return ResponseEntity.ok(saved);
    }
}