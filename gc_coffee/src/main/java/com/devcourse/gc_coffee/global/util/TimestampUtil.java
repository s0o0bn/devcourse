package com.devcourse.gc_coffee.global.util;

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

    public static LocalDateTime getTodayAt(int hour) {
        return LocalDateTime.now()
                .withHour(hour)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }
}
