package com.bartosznowacki.app.authservice.auth.requests;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInRequest {
    private String username;
    String password;
}
