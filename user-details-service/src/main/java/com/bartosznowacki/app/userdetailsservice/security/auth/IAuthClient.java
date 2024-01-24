package com.bartosznowacki.app.userdetailsservice.security.auth;

import com.bartosznowacki.app.userdetailsservice.security.auth.models.CheckUserResponse;

public interface IAuthClient {
    CheckUserResponse authenticate(String authorization);
}
