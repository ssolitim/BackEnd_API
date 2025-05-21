package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ssolitim.child_tracking_system.api.model.Voice;

public interface VoiceRepository extends Repository<Voice, Integer> {

    Voice save(Voice voice);

    List<Voice> findAll();

    Optional<Voice> findById(Integer id);

    void deleteById(Integer id);
}
