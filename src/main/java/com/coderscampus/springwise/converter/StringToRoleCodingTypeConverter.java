package com.coderscampus.springwise.converter;

import com.coderscampus.springwise.domain.Checkin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleCodingTypeConverter implements Converter<String, Checkin.Role> {

    @Override
    public Checkin.Role convert(String source) {
        try {
            return Checkin.Role.valueOf(source.toUpperCase().replace(" ", "_"));
        } catch (Exception e) {
            return null; // or Checkin.Role.DEFAULT if you have a default enum constant
        }
    }
}
