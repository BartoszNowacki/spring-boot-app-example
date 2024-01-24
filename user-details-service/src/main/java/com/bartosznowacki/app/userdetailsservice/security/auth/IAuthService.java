package com.bartosznowacki.app.userdetailsservice.security.auth;

import com.bartosznowacki.app.userdetailsservice.shared.LoggedUserDto;

public interface IAuthService {
    LoggedUserDto authenticate(String authorization);

    Integer getLoggedUserId();
}
