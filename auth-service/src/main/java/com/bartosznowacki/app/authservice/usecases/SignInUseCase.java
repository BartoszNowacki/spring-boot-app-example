package com.bartosznowacki.app.authservice.usecases;

import com.bartosznowacki.app.authservice.auth.requests.SignInRequest;
import com.bartosznowacki.app.authservice.auth.responses.SignInResponse;
import com.bartosznowacki.app.authservice.jwt.IJwtService;
import com.bartosznowacki.app.authservice.token.ITokenService;
import com.bartosznowacki.app.authservice.user.ISecurityUserService;
import com.bartosznowacki.app.authservice.user.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInUseCase {
    private final ISecurityUserService securityUserService;
    private final IJwtService jwtService;
    private final ITokenService tokenService;

    public ResponseEntity<SignInResponse> execute(SignInRequest request) {
        final SecurityUser user = securityUserService.findUserAndCheckPassword(
                request.getUsername(),
                request.getPassword()
        );
        final String token = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, token);
        final SignInResponse response = SignInResponse.builder()
                .accessToken(token)
                .build();
        return ResponseEntity.ok(response);
    }
}
