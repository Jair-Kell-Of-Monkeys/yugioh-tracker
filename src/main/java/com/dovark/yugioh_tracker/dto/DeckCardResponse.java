package com.dovark.yugioh_tracker.dto;

import com.dovark.yugioh_tracker.model.DeckCard;

public record DeckCardResponse(Long id, CardResponse card, int quantity, DeckCard.Section section) {

    public static DeckCardResponse fromEntity(DeckCard deckCard) {
        return new DeckCardResponse(
                deckCard.getId(),
                CardResponse.fromEntity(deckCard.getCard()),
                deckCard.getQuantity(),
                deckCard.getSection()
        );
    }
}