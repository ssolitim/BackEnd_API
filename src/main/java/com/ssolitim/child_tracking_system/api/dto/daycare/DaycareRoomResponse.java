package com.ssolitim.child_tracking_system.api.dto.daycare;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssolitim.child_tracking_system.api.model.DaycareRoom;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DaycareRoomResponse (
    @Schema(description = "어린이집 반 고유 id", example = "2", requiredMode = REQUIRED)
    Integer id,

    @Schema(description = "어린이집 반 이름", example = "해바라기반", requiredMode = REQUIRED)
    String room
){
    public static DaycareRoomResponse from(DaycareRoom daycareRoom) {
        return new DaycareRoomResponse(daycareRoom.getId(), daycareRoom.getRoom());
    }
}
