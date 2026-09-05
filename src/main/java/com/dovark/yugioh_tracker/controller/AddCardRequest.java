package com.dovark.yugioh_tracker.controller;

import com.dovark.yugioh_tracker.model.DeckCard;

public record AddCardRequest(Long cardId, int quantity, DeckCard.Section section) {
}