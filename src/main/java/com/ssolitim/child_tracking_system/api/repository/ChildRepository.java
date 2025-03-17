package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ssolitim.child_tracking_system.api.model.Child;

public interface ChildRepository extends Repository<Child, Integer> {

    Child save(Child child);

    Optional<Child> findById(Integer id);

    void deleteById(Integer id);

    List<Child> findAllByDaycareRoomId(Integer daycareRoomId);
}
