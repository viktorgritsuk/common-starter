package com.common.starter.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * Converter String to boolean.
 */
public final class ConvertUtil {

    private ConvertUtil() {
    }

    /**
     * Converts a string 'Y' or 'N' to a boolean.
     *
     * @param input the string to be converted. If the input is 'Y', it returns {@code true};
     * if the input is 'N', it returns {@code false}; if the input is empty or null,
     * it returns {@code null}.
     * @return the corresponding {@code Boolean} value or {@code null} if the input is empty or null.
     */
    public static Boolean convertToBoolean(String input) {
        return StringUtils.isEmpty(input) ? null : input.equals("Y");
    }

    /**
     * Converts a string 'Y' or 'N' to a boolean.
     *
     * @param input the string to be converted. If the input is 'Y', it returns {@code true};
     * if the input is 'N', it returns {@code false}; if the input is empty or null,
     * it returns {@code false} like default value.
     * @return the corresponding {@code Boolean} value or {@code false} if the input is empty or null.
     */
    public static Boolean convertToBooleanWithDefault(String input) {
        return !StringUtils.isEmpty(input) && input.equals("Y");
    }

    /**
     * Converts a boolean to a string 'Y' or 'N'.
     *
     * @param input the boolean to be converted. If the input is {@code true}, it returns 'Y';
     * if the input is {@code false}, it returns 'N'; if the input is {@code null},
     * it returns 'N'.
     * @return the corresponding string value ('Y' or 'N').
     */
    public static String convertFromBoolean(Boolean input) {
        if (input == null) {
            return "N";
        }
        return input ? "Y" : "N";
    }

    /**
     * Converts a string values to a string as boolean 'Y' or 'N'.
     *
     * @param input the string to be converted.
     * if the input is {@code null}, it returns 'N'.
     * @return the corresponding string value (input or 'N').
     */
    public static String convertFromStringToStringBoolean(String input) {
        if (input == null) {
            return "N";
        }

        return input;
    }

    /**
     * Converts a string to a {@link BigDecimal}.
     *
     * @param input the string to be converted. The input string is expected to represent a decimal
     * number, where commas are replaced with dots to accommodate different locales.
     * If the input is empty or null, it returns {@code null}.
     * @return the corresponding {@code BigDecimal} value or {@code null} if the input is empty or null.
     */
    public static BigDecimal convertToBigDecimal(String input) {

        return StringUtils.isEmpty(input) ? null : new BigDecimal(input.replace(",", "."));
    }

    /**
     * Converts a BigDecimal to a {@link String}.
     *
     * @param input the big decimal to be converted.
     * If the input is null, it returns {@code empty}.
     * @return the corresponding {@code String} value or {@code empty} if the input is null.
     */
    public static String convertFromBigDecimal(BigDecimal input) {
        return input != null ? input.toString() : "";
    }

}
