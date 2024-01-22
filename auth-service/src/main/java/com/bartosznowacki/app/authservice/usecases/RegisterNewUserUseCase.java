package com.bartosznowacki.app.authservice.usecases;

import com.bartosznowacki.app.authservice.auth.requests.RegisterRequest;
import com.bartosznowacki.app.authservice.auth.responses.SignInResponse;
import com.bartosznowacki.app.authservice.jwt.IJwtService;
import com.bartosznowacki.app.authservice.user.ISecurityUserService;
import com.bartosznowacki.app.authservice.user.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterNewUserUseCase {
    private final ISecurityUserService securityUserService;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<SignInResponse> execute(RegisterRequest request) {
        securityUserService.checkIfUserExists(request.getUsername());
        final SecurityUser user = SecurityUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .disabled(false)
                .active(true)
                .role(request.getRole())
                .build();
        securityUserService.saveUser(user);
        final String token = jwtService.generateToken(user);
        final SignInResponse response = SignInResponse.builder()
                .accessToken(token)
                .build();
        return ResponseEntity.ok(response);
    }
}
