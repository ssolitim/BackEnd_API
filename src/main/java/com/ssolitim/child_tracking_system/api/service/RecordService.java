package com.ssolitim.child_tracking_system.api.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
import com.google.firebase.messaging.Notification;
import com.ssolitim.child_tracking_system.api.dto.record.RecordResponse;
import com.ssolitim.child_tracking_system.api.model.Record;
import com.ssolitim.child_tracking_system.api.repository.RecordRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;
    private final FirebaseMessaging firebaseMessaging;
    private static final String IMAGE_STORAGE_ADDRESS = "/home/ubuntu/detect/images/";
    private static final String VIDEO_STORAGE_ADDRESS = "/home/ubuntu/detect/videos/";
    private static final String AUDIO_STORAGE_ADDRESS = "/home/ubuntu/detect/audio/";

    @PersistenceContext
    private EntityManager entityManager;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<RecordResponse> getRecord() {
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .sorted((r1, r2) -> r2.getDate().compareTo(r1.getDate())) // 날짜 최신순 정렬
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public List<RecordResponse> readRecord(Integer recordId) {
        Record record = recordRepository.findById(recordId)
            .orElseThrow(() -> new IllegalArgumentException("기록을 찾을 수 없습니다."));
        record.read();
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public List<RecordResponse> cancelReadRecord(Integer recordId) {
        Record record = recordRepository.findById(recordId)
            .orElseThrow(() -> new IllegalArgumentException("기록을 찾을 수 없습니다."));
        record.unRead();
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public void filesUploadOnServer(MultipartFile[] uploadFiles, String direct) throws FirebaseMessagingException {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        String timestamp = now.format(formatter);

        List<String> fullPathList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String contentType = uploadFile.getContentType();
            assert contentType != null;
            String fullPath = getFullPath(contentType, timestamp);
            fullPathList.add(fullPath);
            try {
                uploadFile.transferTo(new File(fullPath));
            } catch (IOException e) {
                throw new IllegalArgumentException("파일 저장 중 오류 발생", e);
            }
        }

        Record record = Record.builder()
            .image(timestamp + ".jpg")
            .video(timestamp + ".mp4")
            .date(now)
            .build();
        recordRepository.save(record);

        String directMessage;
        if (direct == "left") {
            directMessage = "왼쪽";
        } else if(direct == "straight") {
            directMessage = "직진";
        } else if(direct == "right") {
            directMessage = "오른쪽";
        } else {
            directMessage = "미확인";
        }

        try {
            firebaseMessaging.send(makeMessage(
                "dIYKMNCNQJmVVhwFzWIkeW:APA91bEJWJ1DSF4ASzpjJoLz6_fNZxTtGjvPBXQWShJI6tpqhTTqAEzLoV_hCZBPO-rjtrsb0_mNd0khEmWf-VxRFGGh5NNb7KcBeVquiMeJMHN9y379wew",
                "아동 이탈 감지",
                "아동이 " + directMessage + " 방향으로 " + "이탈이 감지되었습니다.\n 어플을 확인해주세요.")
            );
        } catch (FirebaseMessagingException e) {
            if (e.getMessagingErrorCode() == MessagingErrorCode.INVALID_ARGUMENT) {
                throw new IllegalArgumentException("푸시 알림 토큰이 유효하지 않습니다.");
            } else {
                throw e;
            }
        }
    }

    @Transactional
    public void audioUploadOnServer(MultipartFile audioFile) {
        String fullPath = AUDIO_STORAGE_ADDRESS + "audio" + ".mp3";
        try {
            audioFile.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 저장 중 오류 발생", e);
        }
    }

    @Transactional
    public List<RecordResponse> deleteRecord(Integer recordId) {
        Record record = recordRepository.findById(recordId)
            .orElseThrow(() -> new IllegalArgumentException("기록을 찾을 수 없습니다."));
        record.delete();
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public List<RecordResponse> restoreDeletedRecord() {
        List<Record> records = recordRepository.findAllIncludingDeleted();

        records.forEach(Record::restore);

        recordRepository.saveAll(records);
        entityManager.flush(); // 즉시 반영하여 데이터 일관성 유지

        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public List<RecordResponse> updateRecordMemo(Integer recordId, String memo) {
        Record record = recordRepository.findById(recordId)
            .orElseThrow(() -> new IllegalArgumentException("기록을 찾을 수 없습니다."));
        record.updateMemo(memo);
        recordRepository.save(record);
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }

    @Transactional
    public void testAlarm(String token) throws FirebaseMessagingException {
        try {
            firebaseMessaging.send(makeMessage(
                token,
                "알람 테스트",
                "테스트 알람 메시지입니다.")
            );
        } catch (FirebaseMessagingException e) {
            if (e.getMessagingErrorCode() == MessagingErrorCode.INVALID_ARGUMENT) {
                throw new IllegalArgumentException("푸시 알림 토큰이 유효하지 않습니다.");
            } else {
                throw e;
            }
        }
    }

    private String getFullPath(String contentType, String timestamp) {
        if (contentType.equals("video/mp4")) {
            return VIDEO_STORAGE_ADDRESS + timestamp + ".mp4";
        } else if (contentType.equals("image/jpeg")) {
            return IMAGE_STORAGE_ADDRESS + timestamp + ".jpg";
        } else {
            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
        }
    }

    public static Message makeMessage(String targetToken, String title, String body) {
        Notification notification = Notification
            .builder()
            .setTitle(title)
            .setBody(body)
            .build();
        return Message
            .builder()
            .setNotification(notification)
            .setToken(targetToken)
            .build();
    }
}
