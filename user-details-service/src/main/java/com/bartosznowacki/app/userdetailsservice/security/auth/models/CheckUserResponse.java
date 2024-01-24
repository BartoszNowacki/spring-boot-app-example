package com.bartosznowacki.app.userdetailsservice.security.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserResponse {
    @JsonProperty("user_id")
    private Integer userID;
    private String role;
}
