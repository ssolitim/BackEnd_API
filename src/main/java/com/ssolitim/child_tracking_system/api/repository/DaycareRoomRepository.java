package com.ssolitim.child_tracking_system.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ssolitim.child_tracking_system.api.model.DaycareRoom;

public interface DaycareRoomRepository extends Repository<DaycareRoom, Integer> {

    Optional<DaycareRoom> findById(Integer id);

    List<DaycareRoom> findAllByDaycareId(Integer daycareId);
}
