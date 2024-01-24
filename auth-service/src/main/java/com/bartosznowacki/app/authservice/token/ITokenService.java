package com.bartosznowacki.app.authservice.token;

import com.bartosznowacki.app.authservice.user.SecurityUser;

public interface ITokenService {
    void saveUserToken(SecurityUser user, String jwtToken);

    void revokeAllUserTokens(SecurityUser user);

    boolean validateTokenInDB(String token);
}
