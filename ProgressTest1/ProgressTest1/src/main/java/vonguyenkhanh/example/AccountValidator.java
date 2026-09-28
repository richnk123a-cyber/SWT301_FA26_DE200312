package vonguyenkhanh.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.regex.Pattern;

public class AccountValidator {

    private static final Pattern USERNAME =
            Pattern.compile("^[A-Za-z][A-Za-z0-9_]{4,19}$");

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$");

    private static final Pattern PHONE =
            Pattern.compile("^0[35789]\\d{8}$");

    private static final String SPECIAL_CHARS =
            "!@#$%^&*()_+-=";

    public static boolean isValidUsername(String username) {
        return username != null
                && USERNAME.matcher(username).matches();
    }

    public static boolean isValidPassword(String password, String username) {

        if (password == null
                || password.length() < 8
                || password.length() > 32) {
            return false;
        }

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        for (char c : password.toCharArray()) {

            if (c >= 'A' && c <= 'Z') {
                upper = true;
            } else if (c >= 'a' && c <= 'z') {
                lower = true;
            } else if (c >= '0' && c <= '9') {
                digit = true;
            } else if (SPECIAL_CHARS.indexOf(c) >= 0) {
                special = true;
            } else {
                return false;
            }
        }

        // Phải có đủ 4 nhóm ký tự
        if (!upper || !lower || !digit || !special) {
            return false;
        }

        // Password không được chứa username
        if (username != null
                && password.toLowerCase(Locale.ROOT)
                .contains(username.toLowerCase(Locale.ROOT))) {
            return false;
        }

        return true;
    }

    public static int calculateAge(LocalDate dob, LocalDate today) {
        return Period.between(dob, today).getYears();
    }
}