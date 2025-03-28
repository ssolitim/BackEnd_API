package com.ssolitim.child_tracking_system.api.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssolitim.child_tracking_system.api.dto.record.RecordResponse;
import com.ssolitim.child_tracking_system.api.model.Record;
import com.ssolitim.child_tracking_system.api.repository.RecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;
    private final String IMAGE_STORAGE_ADDRESS = "/home/ubuntu/detect/images";
    private final String VIDEO_STORAGE_ADDRESS = "/home/ubuntu/detect/videos";

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String timestamp = now.format(formatter);

    public List<RecordResponse> getRecord() {
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public void filesUploadOnServer(MultipartFile[] uploadFiles) {
        List<String> fullPathList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String contentType = uploadFile.getContentType();
            assert contentType != null;
            String fullPath = getFullPath(contentType);
            fullPathList.add(fullPath);
            try {
                uploadFile.transferTo(new File(fullPath));
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
        Record record = Record.builder()
            .image(fullPathList.get(0))
            .video(fullPathList.get(1))
            .date(now)
            .memo(null)
            .build();
        recordRepository.save(record);
    }

    private String getFullPath(String contentType) {
        if (contentType.equals("video/mp4")) {
            return VIDEO_STORAGE_ADDRESS + timestamp + ".mp4";
        } else if (contentType.equals("image/jpeg")) {
            return IMAGE_STORAGE_ADDRESS + timestamp + ".jpg";
        } else {
            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
        }
    }
}
