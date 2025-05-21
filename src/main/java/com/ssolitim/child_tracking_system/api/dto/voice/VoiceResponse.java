package com.ssolitim.child_tracking_system.api.dto.voice;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssolitim.child_tracking_system.api.model.Voice;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VoiceResponse(
    @Schema(description = "목소리 고유 번호", example = "1", requiredMode = REQUIRED)
    Integer id,

    @Schema(description = "목소리 내용", example = "나가면 안돼", requiredMode = REQUIRED)
    String content,

    @Schema(description = "선택 여부", example = "true", requiredMode = REQUIRED)
    Boolean isChecked
){
    public static VoiceResponse from(Voice voice) {
        return new VoiceResponse(
            voice.getId(),
            voice.getContent(),
            voice.isChecked()
        );
    }
}
