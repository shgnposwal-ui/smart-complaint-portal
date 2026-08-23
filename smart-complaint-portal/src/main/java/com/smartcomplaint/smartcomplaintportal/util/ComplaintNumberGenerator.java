package com.smartcomplaint.smartcomplaintportal.util;

import java.time.Year;
import java.util.UUID;

public class ComplaintNumberGenerator {

    private ComplaintNumberGenerator() {
    }

    public static String generate() {

        String random = UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();

        return "CMP-"
                + Year.now().getValue()
                + "-"
                + random;
    }
}
