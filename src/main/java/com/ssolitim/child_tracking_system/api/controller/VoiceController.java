package com.ssolitim.child_tracking_system.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssolitim.child_tracking_system.api.dto.voice.VoiceResponse;
import com.ssolitim.child_tracking_system.api.service.VoiceService;

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
@Tag(name = "Voice: 음성", description = "음성 관련 API")
public class VoiceController {

    private final VoiceService voiceService;

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "음성 텍스트 추가")
    @PostMapping("/voice")
    public ResponseEntity<List<VoiceResponse>> addVoice(
        @RequestBody @Valid String content
    ) {
        voiceService.registerVoice(content);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "음성 조회")
    @GetMapping("/voice")
    public ResponseEntity<List<VoiceResponse>> getVoice(
    ) {
        List<VoiceResponse> response = voiceService.getVoice();
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
    @Operation(summary = "음성 삭제")
    @GetMapping("/voice/{voiceId}")
    public ResponseEntity<List<VoiceResponse>> deleteVoice(
        @PathVariable Integer voiceId
    ) {
        List<VoiceResponse> response = voiceService.deleteVoice(voiceId);
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
    @Operation(summary = "음성 선택(기존 음성은 자동으로 선택 해제)")
    @GetMapping("/voice/check/{voiceId}")
    public ResponseEntity<List<VoiceResponse>> checkVoice(
        @PathVariable Integer voiceId
    ) {
        List<VoiceResponse> response = voiceService.checkVoice(voiceId);
        return ResponseEntity.ok(response);
    }
}
