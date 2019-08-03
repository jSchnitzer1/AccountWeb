package com.accounts.web.view;

import com.accounts.web.controller.AccountsFacade;
import org.apache.log4j.PropertyConfigurator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "accountsBean")
public class AccountsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    AccountsFacade accountsFacade;

    @PostConstruct
    public void init() {
        String log4jConfigPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF/log4j.properties");
        PropertyConfigurator.configure(log4jConfigPath);
    }

    public AccountsBean() {
    }

    public String getCheckAccountFacade(){
        return accountsFacade.isAlive() ? "OK 200" : "Server Error";
    }
}