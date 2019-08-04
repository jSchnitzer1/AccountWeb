package com.accounts.web.model;

public class Account {
    private long accountId;
    private double balance;
    private boolean isDefault;
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account() {
    }

    public Account(long accountId, double balance, boolean isDefault, Customer customer) {
        this.accountId = accountId;
        this.balance = balance;
        this.isDefault = isDefault;
        this.customer = customer;
    }
}
