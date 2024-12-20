package com.coderscampus.cp.domain;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InstantFormatter {
    public static String format(Instant instant) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' hh:mm a");

        String formattedDate = zonedDateTime.format(formatter);
        String dayWithSuffix = getDayWithSuffix(zonedDateTime.getDayOfMonth());

        formattedDate = formattedDate.replaceFirst("\\d+", dayWithSuffix);

        return formattedDate;
    }

    private static String getDayWithSuffix(int dayOfMonth) {
        if (dayOfMonth >= 11 && dayOfMonth <= 13) {
            return dayOfMonth + "th";
        }
        return switch (dayOfMonth % 10) {
            case 1 -> dayOfMonth + "st";
            case 2 -> dayOfMonth + "nd";
            case 3 -> dayOfMonth + "rd";
            default -> dayOfMonth + "th";
        };
    }


}
