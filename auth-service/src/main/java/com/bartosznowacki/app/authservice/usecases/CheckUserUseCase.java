package com.bartosznowacki.app.authservice.usecases;

import com.bartosznowacki.app.authservice.auth.responses.CheckUserResponse;
import com.bartosznowacki.app.authservice.shared.LoggedUserDto;
import com.bartosznowacki.app.authservice.user.ISecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserUseCase {
    private final ISecurityUserService securityUserService;

    public ResponseEntity<CheckUserResponse> execute() {
        final LoggedUserDto user = securityUserService.getLoggedUser();
        final CheckUserResponse response = CheckUserResponse.builder()
                .userID(user.getId())
                .role(user.getRoleType().name())
                .build();
        return ResponseEntity.ok(response);
    }
}
