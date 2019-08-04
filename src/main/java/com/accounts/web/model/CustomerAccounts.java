package com.accounts.web.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccounts {
    private Customer customer;
    private List<Account> accounts;

    public CustomerAccounts() {
    }

    public CustomerAccounts(Customer customer, List<Account> accounts) {
        this.customer = customer;
        this.accounts = accounts;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}