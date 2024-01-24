package com.bartosznowacki.app.authservice.usecases;

import com.bartosznowacki.app.authservice.token.ITokenService;
import com.bartosznowacki.app.authservice.user.ISecurityUserService;
import com.bartosznowacki.app.authservice.user.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutUseCase {
    private final ISecurityUserService securityUserService;
    private final ITokenService tokenService;

    public ResponseEntity<Void> execute() {
        final SecurityUser user = securityUserService.getLoggedSecurityUser();
        tokenService.revokeAllUserTokens(user);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
}
