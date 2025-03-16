package com.ssolitim.child_tracking_system.api.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.config.JwtProvider;
import com.ssolitim.child_tracking_system.api.dto.user.UserLoginRequest;
import com.ssolitim.child_tracking_system.api.dto.user.UserLoginResponse;
import com.ssolitim.child_tracking_system.api.dto.user.UserRegisterRequest;
import com.ssolitim.child_tracking_system.api.model.Daycare;
import com.ssolitim.child_tracking_system.api.model.User;
import com.ssolitim.child_tracking_system.api.repository.DaycareRepository;
import com.ssolitim.child_tracking_system.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final DaycareRepository daycareRepository;

    @Transactional
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        if (!Objects.equals(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        String accessToken = jwtProvider.createToken(user);
        return UserLoginResponse.of(accessToken);
    }

    @Transactional
    public void userRegister(UserRegisterRequest request) {
        userRepository.findByUsername(request.username())
            .ifPresent(user -> {
                throw new IllegalArgumentException("이미 존재하는 유저입니다.");
            });

        Daycare daycare = daycareRepository.findById(request.daycareId())
            .orElseThrow(() -> new IllegalArgumentException("어린이집을 찾을 수 없습니다."));

        userRepository.save(User.builder()
            .username(request.username())
            .password(request.password())
            .phone(request.phone())
            .daycare(daycare)
            .build());
    }
}
