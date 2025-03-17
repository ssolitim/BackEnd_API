package com.ssolitim.child_tracking_system.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.api.dto.child.ChildRegisterRequest;
import com.ssolitim.child_tracking_system.api.dto.child.ChildResponse;
import com.ssolitim.child_tracking_system.api.dto.child.ChildUpdateRequest;
import com.ssolitim.child_tracking_system.api.model.Child;
import com.ssolitim.child_tracking_system.api.model.DaycareRoom;
import com.ssolitim.child_tracking_system.api.repository.ChildRepository;
import com.ssolitim.child_tracking_system.api.repository.DaycareRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChildService {

    private final DaycareRoomRepository daycareRoomRepository;
    private final ChildRepository childRepository;

    @Transactional
    public ChildResponse childRegister(ChildRegisterRequest request) {
        DaycareRoom daycareRoom = daycareRoomRepository.findById(request.daycareRoomId())
            .orElseThrow(() -> new IllegalArgumentException("어린이집 반을 찾을 수 없습니다."));
        Child child = Child.builder()
            .name(request.name())
            .age(request.age())
            .gender(request.gender())
            .phone(request.phone())
            .memo(request.memo())
            .daycareRoom(daycareRoom)
            .build();
        childRepository.save(child);
        return ChildResponse.from(child);
    }

    @Transactional
    public ChildResponse childUpdate(Integer childId, ChildUpdateRequest request) {
        DaycareRoom daycareRoom = daycareRoomRepository.findById(request.daycareRoomId())
            .orElseThrow(() -> new IllegalArgumentException("어린이집을 찾을 수 없습니다."));
        Child child = childRepository.findById(childId)
            .orElseThrow(() -> new IllegalArgumentException("아동을 찾을 수 없습니다."));
        child.update(request.name(), request.age(), request.gender(), request.phone(), request.memo(), daycareRoom);
        return ChildResponse.from(child);
    }

    @Transactional
    public void childDelete(Integer childId) {
        childRepository.deleteById(childId);
    }

    public ChildResponse getChild(Integer childId) {
        Child child = childRepository.findById(childId)
            .orElseThrow(() -> new IllegalArgumentException("아동을 찾을 수 없습니다."));
        return ChildResponse.from(child);
    }

    public List<ChildResponse> getDaycareRoomChild(Integer daycareRoomId) {
        DaycareRoom daycareRoom = daycareRoomRepository.findById(daycareRoomId)
            .orElseThrow(() -> new IllegalArgumentException("어린이집 반을 찾을 수 없습니다."));
        List<Child> childList = childRepository.findAllByDaycareRoomId(daycareRoom.getId());
        return childList.stream()
            .map(ChildResponse::from)
            .toList();
    }
}
