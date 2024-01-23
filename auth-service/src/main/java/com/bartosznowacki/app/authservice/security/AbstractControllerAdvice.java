package com.bartosznowacki.app.authservice.security;

import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractControllerAdvice {
    protected final Map<String, String> errorsMap;

    protected AbstractControllerAdvice(Collection<String> collection, Environment environment) {
        errorsMap = createErrorsMap(collection, environment);
    }

    private Map<String, String> createErrorsMap(Collection<String> collection, Environment environment) {
        return collection.stream()
                .filter(key -> Objects.nonNull(environment.getProperty(key)))
                .collect(Collectors.toMap(key -> key, environment::getProperty));
    }
}
