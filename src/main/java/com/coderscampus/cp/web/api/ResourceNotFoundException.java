package com.coderscampus.cp.web.api;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException() {
        super("Not found");
    }
}
