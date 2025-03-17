package com.ssolitim.child_tracking_system.api.model;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "child")
@NoArgsConstructor(access = PROTECTED)
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "memo")
    private String memo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daycare_room_id", nullable = false)
    private DaycareRoom daycareRoom;

    @Builder
    public Child(String name, int age, boolean gender, String phone, String memo, DaycareRoom daycareRoom) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.memo = memo;
        this.daycareRoom = daycareRoom;
    }

    public void update(
        String name,
        int age,
        boolean gender,
        String phone,
        String memo,
        DaycareRoom daycareroom
    ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.memo = memo;
        this.daycareRoom = daycareroom;
    }
}
