package com.coderscampus.cp.web.api;

import java.util.Map;

public class ValidationException extends ApiException {

    public ValidationException(Map<String, String> errors) {
        super("Validation failed", errors);
    }
}
