package com.ssolitim.child_tracking_system.api.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Record: 기록", description = "영상/사진 관련 API")
public class RecordController {
}
