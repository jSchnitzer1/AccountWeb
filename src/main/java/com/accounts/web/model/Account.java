package com.accounts.web.model;

public class Account {
    private long accountId;
    private double balance;
    private boolean defaultAccount;
    private Customer customer;

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

    public Account() {
    }

    public Account(long accountId, double balance, boolean defaultAccount, Customer customer) {
        this.accountId = accountId;
        this.balance = balance;
        this.defaultAccount = defaultAccount;
        this.customer = customer;
    }
}
