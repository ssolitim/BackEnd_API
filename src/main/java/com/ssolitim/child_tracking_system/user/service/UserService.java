package com.ssolitim.child_tracking_system.user.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssolitim.child_tracking_system.config.JwtProvider;
import com.ssolitim.child_tracking_system.user.dto.UserLoginRequest;
import com.ssolitim.child_tracking_system.user.dto.UserLoginResponse;
import com.ssolitim.child_tracking_system.user.dto.UserRegisterRequest;
import com.ssolitim.child_tracking_system.user.model.User;
import com.ssolitim.child_tracking_system.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

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

        userRepository.save(User.builder()
            .username(request.username())
            .password(request.password())
            .build());
    }
}
