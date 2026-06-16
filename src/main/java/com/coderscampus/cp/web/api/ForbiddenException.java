package com.coderscampus.cp.web.api;

public class ForbiddenException extends ApiException {

    public ForbiddenException() {
        super("Forbidden");
    }
}
