package com.bartosznowacki.app.authservice.auth.requests;

import com.bartosznowacki.app.authservice.shared.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Role role;
}
