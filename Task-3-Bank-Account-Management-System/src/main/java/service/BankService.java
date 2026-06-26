package service;

import exception.AccountNotFoundException;
import exception.DuplicateEmailException;
import exception.DuplicateMobileException;
import exception.InsufficientBalanceException;
import model.AccountType;
import model.BankAccount;
import model.Transaction;
import model.TransactionType;
import util.AccountNumberGenerator;
import util.TransactionIdGenerator;
import util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BankService {

    private final List<BankAccount> accounts;

    public BankService() {
        accounts = new ArrayList<>();
    }
    public BankAccount createAccount(String holderName,AccountType accountType,String mobile,String email,double openingBalance) {
        ValidationUtil.validateName(holderName);
        ValidationUtil.validateMobile(mobile);
        ValidationUtil.validateEmail(email);
        ValidationUtil.validateDepositAmount(openingBalance);

        if (isEmailExists(email)) {
            throw new DuplicateEmailException(
                    "Email already exists.");
        }

        if (isMobileExists(mobile)) {
            throw new DuplicateMobileException(
                    "Mobile number already exists.");
        }

        BankAccount account = new BankAccount(
                AccountNumberGenerator.generateAccountNumber(),
                holderName,
                accountType,
                mobile,
                email,
                openingBalance
        );

        Transaction transaction = new Transaction(
                TransactionIdGenerator.generateTransactionId(),
                TransactionType.DEPOSIT,
                openingBalance,
                LocalDateTime.now(),
                openingBalance
        );

        account.addTransaction(transaction);

        accounts.add(account);

        return account;
    }
    public void deposit(String accountNumber,
                        double amount) {

        ValidationUtil.validateDepositAmount(amount);

        BankAccount account = searchAccount(accountNumber);

        account.setBalance(
                account.getBalance() + amount
        );

        Transaction transaction = new Transaction(
                TransactionIdGenerator.generateTransactionId(),
                TransactionType.DEPOSIT,
                amount,
                LocalDateTime.now(),
                account.getBalance()
        );

        account.addTransaction(transaction);
    }

    public void withdraw(String accountNumber,
                        double amount) {

        ValidationUtil.validateWithdrawalAmount(amount);

        BankAccount account = searchAccount(accountNumber);

        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException(
                    "Insufficient balance."
            );
        }

        account.setBalance(
                account.getBalance() - amount
        );

        Transaction transaction = new Transaction(
                TransactionIdGenerator.generateTransactionId(),
                TransactionType.WITHDRAWAL,
                amount,
                LocalDateTime.now(),
                account.getBalance()
        );

        account.addTransaction(transaction);
    }

    public double checkBalance(String accountNumber) {

        return searchAccount(accountNumber)
                .getBalance();
    }

    public List<Transaction> viewTransactionHistory(
            String accountNumber) {

        return searchAccount(accountNumber)
                .getTransactionHistory();
    }

    public BankAccount searchAccount(
            String accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber()
                    .equalsIgnoreCase(accountNumber)) {

                return account;
            }
        }

        throw new AccountNotFoundException(
                "Account not found."
        );
    }

    public boolean isEmailExists(String email) {
        for (BankAccount account : accounts) {
            if (account.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMobileExists(String mobile) {
        for (BankAccount account : accounts) {
            if (account.getMobileNumber().equals(mobile)) {
                return true;
            }
        }
        return false;
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts);
    }
    public List<BankAccount> searchByHolderName(String holderName) {

        List<BankAccount> matchingAccounts = new ArrayList<>();

        for (BankAccount account : accounts) {

            if (account.getAccountHolderName()
                    .toLowerCase()
                    .contains(holderName.toLowerCase())) {

                matchingAccounts.add(account);
            }
        }

        return matchingAccounts;
    }
    public void sortAccountsAlphabetically() {

        accounts.sort(
                Comparator.comparing(
                        BankAccount::getAccountHolderName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );
    }
    public int getTotalAccounts() {
        return accounts.size();
    }
    public double getTotalBankBalance() {

        double total = 0;

        for (BankAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }

    public void deleteAccount(String accountNumber) {

        BankAccount account = searchAccount(accountNumber);

        accounts.remove(account);
    }

    public List<Transaction> getMiniStatement(String accountNumber) {

        List<Transaction> history =
                searchAccount(accountNumber)
                        .getTransactionHistory();

        int start = Math.max(0, history.size() - 5);

        return new ArrayList<>(history.subList(start, history.size()));
    }
}