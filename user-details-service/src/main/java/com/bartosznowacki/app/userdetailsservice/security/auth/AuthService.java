package com.bartosznowacki.app.userdetailsservice.security.auth;

import com.bartosznowacki.app.userdetailsservice.security.auth.models.CheckUserResponse;
import com.bartosznowacki.app.userdetailsservice.shared.LoggedUserDto;
import com.bartosznowacki.app.userdetailsservice.shared.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AuthService implements IAuthService {
    final IAuthClient authClient;

    public LoggedUserDto authenticate(String authorization) {
        final CheckUserResponse response = authClient.authenticate(authorization);
        return LoggedUserDto.builder()
                .id(response.getUserID())
                .roleType(Role.valueOf(response.getRole()))
                .build();
    }

    public Integer getLoggedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoggedUserDto user = (LoggedUserDto) authentication.getPrincipal();
        return user.getId();
    }
}
