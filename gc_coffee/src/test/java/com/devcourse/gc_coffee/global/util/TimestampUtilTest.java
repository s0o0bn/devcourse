package com.devcourse.gc_coffee.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimestampUtilTest {
    @Test
    @DisplayName("Timestamp를 날짜 형식으로 포맷팅한다.")
    void format_timestamp_by_pattern() {
        // given
        LocalDateTime timestamp = LocalDateTime.now()
                .withYear(2024)
                .withMonth(9)
                .withDayOfMonth(8)
                .withHour(12)
                .withMinute(30)
                .withSecond(0);
        String expected = "2024-09-08 12:30:00";

        // when
        String actual = TimestampUtil.format(timestamp);

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("오늘 특정 시간을 나타내는 LocalDateTime 객체를 생성한다.")
    void create_LocalDateTime_at_specific_hour_of_today() {
        // given
        int hour = 14;

        // when
        LocalDateTime actual = TimestampUtil.getTodayAt(hour);

        // then
        assertEquals(actual.getYear(), LocalDateTime.now().getYear());
        assertEquals(actual.getMonth(), LocalDateTime.now().getMonth());
        assertEquals(actual.getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
        assertEquals(actual.getHour(), hour);
    }
}
