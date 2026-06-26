package model;

import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private double balanceAfterTransaction;

    public Transaction(String transactionId,
                       TransactionType transactionType,
                       double amount,
                       LocalDateTime transactionDate,
                       double balanceAfterTransaction) {

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return String.format(
                """
                ----------------------------------------
                Transaction ID   : %s
                Type             : %s
                Amount           : ₹%.2f
                Date & Time      : %s
                Balance After Tx : ₹%.2f
                ----------------------------------------
                """,
                transactionId,
                transactionType,
                amount,
                transactionDate,
                balanceAfterTransaction
        );
    }
}