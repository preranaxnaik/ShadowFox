package util;

public final class AccountNumberGenerator {

    private static int counter = 100001;

    private AccountNumberGenerator() {
        // Prevent instantiation
    }

    public static String generateAccountNumber() {
        return "ACC" + counter++;
    }
}