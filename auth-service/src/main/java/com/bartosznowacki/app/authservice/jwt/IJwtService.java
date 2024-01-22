package com.bartosznowacki.app.authservice.jwt;

import com.bartosznowacki.app.authservice.user.SecurityUser;

public interface IJwtService {
    String extractUsername(String token);

    String generateToken(SecurityUser user);

    boolean validate(String jwt);
}