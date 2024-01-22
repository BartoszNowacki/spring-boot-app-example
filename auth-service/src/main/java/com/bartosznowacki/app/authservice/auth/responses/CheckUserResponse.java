package com.bartosznowacki.app.authservice.auth.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserResponse {
    @JsonProperty("user_id")
    private Integer userID;
    private String role;
}
