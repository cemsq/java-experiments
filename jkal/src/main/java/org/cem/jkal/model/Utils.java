package org.cem.jkal.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 */
public class Utils {

    public static double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double toFactor(double v, double vFactor, double target) {
        return v * target / vFactor;
    }
}
