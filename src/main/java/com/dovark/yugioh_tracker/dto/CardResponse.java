package com.dovark.yugioh_tracker.dto;

import com.dovark.yugioh_tracker.model.Card;

public record CardResponse(Long id, String name, String archetype, Card.CardType type) {

    public static CardResponse fromEntity(Card card) {
        return new CardResponse(card.getId(), card.getName(), card.getArchetype(), card.getType());
    }
}