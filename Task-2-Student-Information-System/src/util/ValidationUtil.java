package util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void validateName(String name) {

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");

        if (name.trim().length() < 3)
            throw new IllegalArgumentException(
                    "Name must contain at least 3 characters.");
    }

    public static void validateAge(int age) {

        if (age < 16 || age > 35)
            throw new IllegalArgumentException(
                    "Age must be between 16 and 35.");
    }

    public static void validateEmail(String email) {

        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Email cannot be empty.");

        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new IllegalArgumentException(
                    "Invalid email format.");
    }

    public static void validateSemester(int semester) {

        if (semester < 1 || semester > 8)
            throw new IllegalArgumentException(
                    "Semester must be between 1 and 8.");
    }

    public static void validateCGPA(double cgpa) {

        if (cgpa < 0 || cgpa > 10)
            throw new IllegalArgumentException(
                    "CGPA must be between 0.0 and 10.0.");
    }
}