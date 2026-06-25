package util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Regex patterns
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    /**
     * Validates contact name.
     */
    public static boolean validateName(String name) {

        if (name == null) {
            return false;
        }

        name = name.trim();

        return !name.isEmpty() && name.length() >= 3;
    }

    /**
     * Validates phone number.
     */
    public static boolean validatePhone(String phone) {

        if (phone == null) {
            return false;
        }

        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Validates email address.
     */
    public static boolean validateEmail(String email) {

        if (email == null) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email).matches();
    }

}