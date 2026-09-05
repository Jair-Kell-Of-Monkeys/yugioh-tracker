package com.dovark.yugioh_tracker.repository;

import com.dovark.yugioh_tracker.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}