package com.ssolitim.child_tracking_system.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssolitim.child_tracking_system.api.dto.daycare.DaycareResponse;
import com.ssolitim.child_tracking_system.api.dto.daycare.DaycareRoomResponse;
import com.ssolitim.child_tracking_system.api.service.DaycareService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Daycare: 어린이집", description = "어린이집 API")
public class DaycareController {

    private final DaycareService daycareService;

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        }
    )
    @Operation(summary = "어린이집 조회")
    @GetMapping("/daycare")
    ResponseEntity<List<DaycareResponse>> getDaycare() {
        List<DaycareResponse> daycareList =  daycareService.getDaycare();
        return ResponseEntity.ok(daycareList);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        }
    )
    @Operation(summary = "어린이집 반 조회")
    @GetMapping("/daycare/{daycareId}/rooms")
    ResponseEntity<List<DaycareRoomResponse>> getDaycareRooms(
        @PathVariable Integer daycareId
    ) {
        List<DaycareRoomResponse> daycareList =  daycareService.getDaycareRooms(daycareId);
        return ResponseEntity.ok(daycareList);
    }
}
