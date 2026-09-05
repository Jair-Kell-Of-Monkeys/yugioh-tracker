package com.dovark.yugioh_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String archetype;

    @Enumerated(EnumType.STRING)
    private Format format;

    private LocalDate createdDate;

    public Deck() {
    }

    public Deck(String name, String archetype, Format format) {
        this.name = name;
        this.archetype = archetype;
        this.format = format;
        this.createdDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public enum Format {
        TCG_MODERN, EDISON, HAT, GOAT, MASTER_DUEL
    }
}