package com.ssolitim.child_tracking_system.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssolitim.child_tracking_system.api.dto.child.ChildRegisterRequest;
import com.ssolitim.child_tracking_system.api.dto.child.ChildResponse;
import com.ssolitim.child_tracking_system.api.dto.child.ChildUpdateRequest;
import com.ssolitim.child_tracking_system.api.service.ChildService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Child: 아동", description = "아동 관련 API")
public class ChildController {

    private final ChildService childService;

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "아동 등록")
    @PostMapping("/child/register")
    ResponseEntity<ChildResponse> childRegister(
        @RequestBody @Valid ChildRegisterRequest request
    ) {
        ChildResponse response = childService.childRegister(request);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "아동 정보 수정")
    @PutMapping("/child/{childId}")
    ResponseEntity<ChildResponse> childUpdate(
        @PathVariable Integer childId,
        @RequestBody @Valid ChildUpdateRequest request
    ) {
        ChildResponse response = childService.childUpdate(childId, request);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "아동 삭제")
    @DeleteMapping("/child/{childId}")
    ResponseEntity<Void> deleteChild(
        @PathVariable Integer childId
    ) {
        childService.childDelete(childId);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "아동 조회")
    @GetMapping("/child/{childId}")
    ResponseEntity<ChildResponse> getChild(
        @PathVariable Integer childId
    ) {
        ChildResponse response = childService.getChild(childId);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "어린이집 특정 반의 아동 조회")
    @GetMapping("/daycare/room/{daycareRoomId}/child")
    ResponseEntity<List<ChildResponse>> getDaycareRoomChild(
        @PathVariable Integer daycareRoomId
    ) {
        List<ChildResponse> response = childService.getDaycareRoomChild(daycareRoomId);
        return ResponseEntity.ok(response);
    }
}
