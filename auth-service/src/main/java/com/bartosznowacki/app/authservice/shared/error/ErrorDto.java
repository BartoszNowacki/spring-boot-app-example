package com.bartosznowacki.app.authservice.shared.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private String code;
    private String message;

    @Override
    public String toString() {
        return "ErrorDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
