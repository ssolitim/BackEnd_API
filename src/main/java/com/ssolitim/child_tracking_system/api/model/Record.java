package com.ssolitim.child_tracking_system.api.model;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

import com.ssolitim.child_tracking_system.config.LocalDateTimeAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "record")
@NoArgsConstructor(access = PROTECTED)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image")
    private String image;

    @Column(name = "video")
    private String video;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "memo")
    private String memo;

    @Column(name = "is_read")
    private boolean isRead = false;

    @Builder
    public Record(String image, String video, LocalDateTime date, String memo) {
        this.image = image;
        this.video = video;
        this.date = date;
        this.memo = memo;
    }

    public void read() {
        this.isRead = true;
    }

    public void updateMemo(String memo) {
        this.memo = memo;
    }
}
