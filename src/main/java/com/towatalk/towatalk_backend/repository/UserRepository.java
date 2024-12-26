package com.towatalk.towatalk_backend.repository;

import com.towatalk.towatalk_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
