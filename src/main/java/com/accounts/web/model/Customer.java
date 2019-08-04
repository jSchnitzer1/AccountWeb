package com.accounts.web.model;

import java.util.List;

public class Customer {
    private long customerId;
    private String firstname;
    private String lastname;
    private List<Account> accounts;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Customer() {
    }

    public Customer(long customerId, String firstname, String lastname, List<Account> accounts) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accounts = accounts;
    }
}
