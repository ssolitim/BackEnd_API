package com.ssolitim.child_tracking_system.api.dto.daycare;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssolitim.child_tracking_system.api.model.Daycare;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DaycareResponse (

    @Schema(description = "어린이집 고유 id", example = "1", requiredMode = REQUIRED)
    Integer id,

    @Schema(description = "어린이집 이름", example = "한기대 어린이집", requiredMode = REQUIRED)
    String name
) {
    public static DaycareResponse from(Daycare daycare) {
        return new DaycareResponse(daycare.getId(), daycare.getName());
    }
}
