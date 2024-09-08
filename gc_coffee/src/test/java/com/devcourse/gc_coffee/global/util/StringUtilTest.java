package com.devcourse.gc_coffee.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {
    @Test
    @DisplayName("숫자를 가격 형식으로 포맷팅한다.")
    void format_given_number_to_price() {
        // given
        long number = 15000L;
        String expected = "15,000";

        // when
        String actual = StringUtil.formatNumber2Price(number);

        // then
        assertEquals(actual, expected);
    }
}
