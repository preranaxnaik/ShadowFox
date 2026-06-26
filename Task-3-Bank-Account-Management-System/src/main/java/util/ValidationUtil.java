package util;

import exception.InvalidAmountException;
import exception.ValidationException;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static void validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Account holder name cannot be empty.");
        }

        if (name.trim().length() < 3) {
            throw new ValidationException("Account holder name must contain at least 3 characters.");
        }
    }

    public static void validateMobile(String mobile) {

        if (mobile == null || !mobile.matches("\\d{10}")) {
            throw new ValidationException("Mobile number must contain exactly 10 digits.");
        }
    }

    public static void validateEmail(String email) {

        if (email == null ||
                !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new ValidationException("Invalid email address.");
        }
    }

    public static void validateDepositAmount(double amount) {

        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
    }

    public static void validateWithdrawalAmount(double amount) {

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
    }
}