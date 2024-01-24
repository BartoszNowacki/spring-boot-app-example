package com.bartosznowacki.app.authservice.auth;

import com.bartosznowacki.app.authservice.auth.requests.RegisterRequest;
import com.bartosznowacki.app.authservice.auth.requests.SignInRequest;
import com.bartosznowacki.app.authservice.auth.responses.CheckUserResponse;
import com.bartosznowacki.app.authservice.auth.responses.SignInResponse;
import com.bartosznowacki.app.authservice.usecases.CheckUserUseCase;
import com.bartosznowacki.app.authservice.usecases.LogoutUseCase;
import com.bartosznowacki.app.authservice.usecases.RegisterNewUserUseCase;
import com.bartosznowacki.app.authservice.usecases.SignInUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
    private final CheckUserUseCase checkUserUseCase;
    private final RegisterNewUserUseCase registerNewUserUseCase;
    private final SignInUseCase signInUseCase;
    private final LogoutUseCase logoutUseCase;

    @PostMapping("/public/register")
    public ResponseEntity<SignInResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return registerNewUserUseCase.execute(request);
    }

    @PostMapping("/public/sign-in")
    public ResponseEntity<SignInResponse> signIn(
            @RequestBody SignInRequest request
    ) {
        return signInUseCase.execute(request);
    }

    @PostMapping("/private/logout")
    public ResponseEntity<Void> logout() {
        return logoutUseCase.execute();
    }

    @GetMapping("/private/check-user")
    public ResponseEntity<CheckUserResponse> checkUser() {
        return checkUserUseCase.execute();
    }
}
