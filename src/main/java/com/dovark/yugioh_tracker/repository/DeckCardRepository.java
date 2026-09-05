package com.dovark.yugioh_tracker.repository;

import com.dovark.yugioh_tracker.model.DeckCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {

    List<DeckCard> findByDeckId(Long deckId);
}