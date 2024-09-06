package com.devcourse.gc_coffee.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtil {
    private TimestampUtil() {}

    public static String format(LocalDateTime timestamp) {
        if (timestamp == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
}
