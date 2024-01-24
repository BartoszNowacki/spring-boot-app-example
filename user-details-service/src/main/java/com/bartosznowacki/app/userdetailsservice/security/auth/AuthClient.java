package com.bartosznowacki.app.userdetailsservice.security.auth;

import com.bartosznowacki.app.userdetailsservice.security.auth.models.CheckUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Controller
@AllArgsConstructor
public class AuthClient implements IAuthClient {
    final String baseUrl = "http://localhost:8989/";
    final String authServicePath = "AUTHENTICATION/v1/";

    public CheckUserResponse authenticate(String authorization) {
        final String url = baseUrl + authServicePath + "private/check-user";
        final HttpHeaders httpHeaders = new HttpHeaders();
        final HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null, httpHeaders);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, authorization);
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CheckUserResponse> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                CheckUserResponse.class
        );
        return exchange.getBody();
    }

}
