package com.ssolitim.child_tracking_system.api.dto.record;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssolitim.child_tracking_system.api.model.Record;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RecordResponse(
    @Schema(description = "기록 고유 번호", example = "1", requiredMode = REQUIRED)
    Integer id,

    @Schema(description = "이미지 경로", example = "1", requiredMode = REQUIRED)
    String image,

    @Schema(description = "비디오 경로", example = "1", requiredMode = REQUIRED)
    String video,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "탐지 일시", example = "2025-03-15 12:00:00", requiredMode = REQUIRED)
    LocalDateTime date,

    @Schema(description = "메모", example = "외출", requiredMode = REQUIRED)
    String memo,

    @Schema(description = "읽음 여부", example = "true", requiredMode = REQUIRED)
    Boolean isRead
){
    public static RecordResponse from(Record record) {
        return new RecordResponse(
            record.getId(),
            record.getImage(),
            record.getVideo(),
            record.getDate(),
            record.getMemo(),
            record.isRead()
        );
    }
}
