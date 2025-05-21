package com.ssolitim.child_tracking_system.api.model;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "voice")
@NoArgsConstructor(access = PROTECTED)
public class Voice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "is_checked")
    private boolean isChecked = false;

    @Builder
    public Voice(String content) {
        this.content = content;
    }

    public void check() {
        this.isChecked = true;
    }

    public void uncheck() {
        this.isChecked = false;
    }
}
