package com.coderscampus.cp.web.api;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException() {
        super("Unauthorized");
    }
}
