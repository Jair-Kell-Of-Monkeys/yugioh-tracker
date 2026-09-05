package com.dovark.yugioh_tracker.repository;

import com.dovark.yugioh_tracker.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}