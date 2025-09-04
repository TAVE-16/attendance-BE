package com.tave.attendance.domain.member.utils;

public final class PhoneNumberUtils {
    private PhoneNumberUtils() {}

    public static String toDigits(String raw) {
        if (raw == null) return "";
        return raw.replaceAll("[^0-9]", "");
    }

    public static String last8(String raw) {
        String digits = toDigits(raw);
        return digits.length() >= 8 ? digits.substring(digits.length() - 8) : digits;
    }
}