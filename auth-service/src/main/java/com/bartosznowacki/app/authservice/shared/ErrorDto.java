package com.bartosznowacki.app.authservice.shared;

import lombok.Getter;

@Getter
public class ErrorDto {
    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorDto{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
}
