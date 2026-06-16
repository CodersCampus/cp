package com.coderscampus.cp.web.api;

import java.util.Collections;
import java.util.Map;

public class ApiException extends RuntimeException {

    private final Map<String, String> errors;

    public ApiException(String message) {
        this(message, Collections.emptyMap());
    }

    public ApiException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
