package com.ssolitim.child_tracking_system.api.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ssolitim.child_tracking_system.api.model.User;

public interface UserRepository extends Repository<User, Integer> {
    User save(User user);

    Optional<User> findByUsername(String username);
}
