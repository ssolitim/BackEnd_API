package com.ssolitim.child_tracking_system.user.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ssolitim.child_tracking_system.user.model.User;

public interface UserRepository extends Repository<User, Integer> {
    User save(User user);

    Optional<User> findByUsername(String username);
}
