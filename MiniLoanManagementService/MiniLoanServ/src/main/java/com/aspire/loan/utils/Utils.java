package com.aspire.loan.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static String formatDouble(double value) {
        return BigDecimal.valueOf(value)
                .setScale(3, RoundingMode.HALF_UP)
                .toPlainString();
    }
}
