package com.bartosznowacki.app.authservice.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Integer> {
    Optional<SecurityUser> findSecurityUserByUsername(String username);
}
