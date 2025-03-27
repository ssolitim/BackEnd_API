package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import com.ssolitim.child_tracking_system.api.model.Record;

public interface RecordRepository extends Repository<Record, Integer> {

    List<Record> findAll();
}
