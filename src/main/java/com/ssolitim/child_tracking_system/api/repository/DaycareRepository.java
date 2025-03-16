package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ssolitim.child_tracking_system.api.model.Daycare;

public interface DaycareRepository extends Repository<Daycare, Integer> {
    Daycare save(Daycare daycare);

    Optional<Daycare> findById(Integer id);

    List<Daycare> findAll();
}
