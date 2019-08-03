package com.accounts.web.view;

import com.accounts.web.controller.AccountFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "welcome", eager = true)
public class AccountsBean {
    @EJB
    AccountFacade accountFacade;

    @PostConstruct
    public void init() {
    }

    public AccountsBean() {

    }
    public String getStatus() {
        return accountFacade.isAlive() ? "OK 200" : "Server Error";
    }
}