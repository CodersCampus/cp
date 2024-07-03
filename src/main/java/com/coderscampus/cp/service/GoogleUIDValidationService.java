package com.coderscampus.cp.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class GoogleUIDValidationService {

    private static final String GOOGLE_UID_REGEX = "^[a-zA-Z0-9-]{28,36}$";
    private static final Pattern pattern = Pattern.compile(GOOGLE_UID_REGEX);

    public Boolean isValidGoogleUID(String uid) {
        if (uid == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(uid);
        return matcher.matches();
    }

}
