package com.accounts.web.controller;

import javax.ejb.Stateless;

@Stateless
public class AccountsFacade {
    public AccountsFacade() {
    }

    public boolean isAlive() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return true;
    }
}
