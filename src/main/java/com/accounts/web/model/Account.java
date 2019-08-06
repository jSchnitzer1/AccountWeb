package com.accounts.web.model;

import java.util.List;

public class Account {
    private long accountId;
    private double balance;
    private boolean defaultAccount;
    private Customer customer;
    private List<Transaction> transactions;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account() {
    }

    public Account(long accountId, double balance, boolean defaultAccount, Customer customer, List<Transaction> transactions) {
        this.accountId = accountId;
        this.balance = balance;
        this.defaultAccount = defaultAccount;
        this.customer = customer;
        this.transactions = transactions;
    }
}
