package com.alevel.java.nix.mobileoperator.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class Timestamps {

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "modified_at", nullable = false)
    private Instant modifiedAt;

    public Timestamps() {
    }

    public Timestamps(Instant createdAt, Instant modifiedAt) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Timestamps now() {
        var now = Instant.now();
        return new Timestamps(now, now);
    }

}
