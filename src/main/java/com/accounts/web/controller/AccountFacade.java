package com.accounts.web.controller;

import javax.ejb.Stateless;

@Stateless
public class AccountFacade {
    public AccountFacade() {
    }

    public boolean isAlive() {
        return true;
    }
}
