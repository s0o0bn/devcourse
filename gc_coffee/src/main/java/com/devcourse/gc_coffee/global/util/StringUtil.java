package com.devcourse.gc_coffee.global.util;

import java.text.NumberFormat;
import java.util.Locale;

public class StringUtil {
    private StringUtil() {}

    public static String formatNumber2Price(long price) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        return numberFormat.format(price);
    }
}
