package com.ssolitim.child_tracking_system.api.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssolitim.child_tracking_system.api.dto.record.RecordResponse;
import com.ssolitim.child_tracking_system.api.service.RecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Record: 기록", description = "영상/사진 관련 API")
public class RecordController {

    private final RecordService recordService;

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "사진/영상 기록 경로 조회")
    @GetMapping("/record")
    ResponseEntity<List<RecordResponse>> getRecord(
    ) {
        List<RecordResponse> response = recordService.getRecord();
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
    @Operation(summary = "JPG 사진 조회")
    @GetMapping(
        value = "/record/image/{imageName}",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getImageWithMediaType(
        @PathVariable("imageName")String imageName
    ) throws IOException {
        InputStream imageStream = new FileInputStream("/home/ubuntu/detect/images/" + imageName);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "MP4 동영상 조회")
    @GetMapping(
        value = "/record/video/{videoName}",
        produces = "video/mp4"
    )
    public ResponseEntity<byte[]> getVideoWithMediaType(
        @PathVariable("videoName") String videoName
    ) throws IOException {
        InputStream videoStream = new FileInputStream("/home/ubuntu/detect/videos/" + videoName);
        byte[] videoByteArray = IOUtils.toByteArray(videoStream);
        videoStream.close();
        return new ResponseEntity<byte[]>(videoByteArray, HttpStatus.OK);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "AI서버 API 전용 - JPG/MP4 업로드 (첫번째:jpg, 두번째:mp4)")
    @PostMapping("/record/upload")
    public void filesUpload(@RequestPart MultipartFile[] uploadFiles) {
        recordService.filesUploadOnServer(uploadFiles);
    }

    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
        }
    )
    @Operation(summary = "알림 읽음 확인")
    @PostMapping("/record/read/{recordId}")
    public ResponseEntity<List<RecordResponse>> readRecord(
        @PathVariable Integer recordId
    ) {
        List<RecordResponse> response = recordService.readRecord(recordId);
        return ResponseEntity.ok(response);
    }
}
