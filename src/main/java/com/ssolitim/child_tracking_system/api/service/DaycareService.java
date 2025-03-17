package com.ssolitim.child_tracking_system.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.api.dto.daycare.DaycareResponse;
import com.ssolitim.child_tracking_system.api.dto.daycare.DaycareRoomResponse;
import com.ssolitim.child_tracking_system.api.model.Daycare;
import com.ssolitim.child_tracking_system.api.repository.DaycareRepository;
import com.ssolitim.child_tracking_system.api.repository.DaycareRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DaycareService {

    private final DaycareRepository daycareRepository;
    private final DaycareRoomRepository daycareRoomRepository;

    public List<DaycareResponse> getDaycare() {
        List<Daycare> daycareList = daycareRepository.findAll();
        return daycareList.stream()
            .map(DaycareResponse::from)
            .toList();
    }

    public List<DaycareRoomResponse> getDaycareRooms(Integer daycareId) {
        Daycare daycare = daycareRepository.findById(daycareId)
            .orElseThrow(() -> new IllegalArgumentException("어린이집을 찾을 수 없습니다."));
        return daycareRoomRepository.findAllByDaycareId(daycare.getId()).stream()
            .map(DaycareRoomResponse::from)
            .toList();
    }
}
