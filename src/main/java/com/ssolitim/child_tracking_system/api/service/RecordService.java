package com.ssolitim.child_tracking_system.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.api.dto.record.RecordResponse;
import com.ssolitim.child_tracking_system.api.model.Record;
import com.ssolitim.child_tracking_system.api.repository.RecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;

    public List<RecordResponse> getRecord() {
        List<Record> records = recordRepository.findAll();
        return records.stream()
            .map(RecordResponse::from)
            .toList();
    }
}
