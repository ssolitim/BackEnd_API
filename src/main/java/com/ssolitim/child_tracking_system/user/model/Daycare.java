package com.ssolitim.child_tracking_system.user.model;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "daycare")
@NoArgsConstructor(access = PROTECTED)
public class Daycare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Column(name = "room", length = 255, nullable = false)
    private String room;

    @Builder
    public Daycare(String name, String room) {
        this.name = name;
        this.room = room;
    }
}
