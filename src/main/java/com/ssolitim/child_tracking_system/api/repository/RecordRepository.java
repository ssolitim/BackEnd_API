package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;
import com.ssolitim.child_tracking_system.api.model.Record;

public interface RecordRepository extends Repository<Record, Integer> {

    List<Record> findAll();

    Record save(Record record);

    Optional<Record> findById(Integer id);

    void deleteById(Integer id);
}
