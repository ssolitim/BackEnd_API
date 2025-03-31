package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssolitim.child_tracking_system.api.model.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    List<Record> findAll();

    Record save(Record record);

    Optional<Record> findById(Integer id);

    @Query(value = "SELECT * FROM record", nativeQuery = true)
    List<Record> findAllIncludingDeleted();
}
