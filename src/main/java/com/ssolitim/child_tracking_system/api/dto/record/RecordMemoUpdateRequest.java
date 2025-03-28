package com.ssolitim.child_tracking_system.api.dto.record;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RecordMemoUpdateRequest (
    @Schema(description = "메모", example = "부모와 외출", requiredMode = REQUIRED)
    @NotBlank(message = "메모를 입력해주세요.")
    String memo
) {
}
