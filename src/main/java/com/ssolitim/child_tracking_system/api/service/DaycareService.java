package com.ssolitim.child_tracking_system.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.api.dto.daycare.DaycareResponse;
import com.ssolitim.child_tracking_system.api.model.Daycare;
import com.ssolitim.child_tracking_system.api.repository.DaycareRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DaycareService {

    private final DaycareRepository daycareRepository;

    public List<DaycareResponse> getDaycares() {
        List<Daycare> daycareList = daycareRepository.findAll();
        return daycareList.stream()
            .map(DaycareResponse::from)
            .toList();
    }
}
