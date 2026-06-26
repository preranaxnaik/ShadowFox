package util;

import java.util.UUID;

public final class TransactionIdGenerator {

    private TransactionIdGenerator() {
        // Prevent instantiation
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}