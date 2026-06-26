package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private AccountType accountType;
    private String mobileNumber;
    private String email;
    private double balance;
    private LocalDateTime createdOn;

    private final List<Transaction> transactionHistory;

    public BankAccount(String accountNumber,
                       String accountHolderName,
                       AccountType accountType,
                       String mobileNumber,
                       String email,
                       double balance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.balance = balance;
        this.createdOn = LocalDateTime.now();
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    @Override
    public String toString() {

        return String.format(
                """
                ==========================================
                        ACCOUNT INFORMATION
                ==========================================
                Account Number : %s
                Holder Name    : %s
                Account Type   : %s
                Mobile Number  : %s
                Email          : %s
                Balance        : ₹%.2f
                Created On     : %s
                ==========================================
                """,
                accountNumber,
                accountHolderName,
                accountType,
                mobileNumber,
                email,
                balance,
                createdOn
        );
    }
}