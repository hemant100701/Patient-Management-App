package com.hemendra.auth_service.Repository;

import com.hemendra.auth_service.dto.LoginRequestDto;
import com.hemendra.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepo extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
