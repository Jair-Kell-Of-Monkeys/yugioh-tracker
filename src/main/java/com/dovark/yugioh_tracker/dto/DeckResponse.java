package com.dovark.yugioh_tracker.dto;

import com.dovark.yugioh_tracker.model.Deck;

import java.time.LocalDate;

public record DeckResponse(Long id, String name, String archetype, Deck.Format format, LocalDate createdDate) {

    public static DeckResponse fromEntity(Deck deck) {
        return new DeckResponse(deck.getId(), deck.getName(), deck.getArchetype(), deck.getFormat(), deck.getCreatedDate());
    }
}