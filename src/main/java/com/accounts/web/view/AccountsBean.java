package com.accounts.web.view;

import com.accounts.web.controller.AccountsFacade;
import com.accounts.web.model.Account;
import com.accounts.web.model.Customer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

@ManagedBean(name = "accountsBean")
public class AccountsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AccountsBean.class.getName());
    private static final Properties PROPERTIES = new Properties();
    private static String baseURL;
    private String commandResult;
    private List<Customer> customers;
    private String selectedCustomerId;
    private String selectedInitialAmount;
    @EJB
    AccountsFacade accountsFacade;

    public static String getBaseURL() {
        return baseURL;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public String getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(String selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    public String getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    public String getSelectedInitialAmount() {
        return selectedInitialAmount;
    }

    public void setSelectedInitialAmount(String selectedInitialAmount) {
        this.selectedInitialAmount = selectedInitialAmount;
    }

    @PostConstruct
    public void init() {
        String log4jConfigPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF/log4j.properties");
        PropertyConfigurator.configure(log4jConfigPath);
        InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/service.properties");
        if (inputStream != null) {
            try {
                PROPERTIES.load(inputStream);
            } catch (IOException e) {
                LOGGER.error("postConst - unable to load service properties file. Error: ", e);
            }
        }
        baseURL = !PROPERTIES.isEmpty() ? PROPERTIES.getProperty("accountsApiBaseUrl") : "http://localhost:8080/RestAccountsAPI";
    }

    public AccountsBean() {
    }

    public void generateCustomers(boolean showCommandResult) {
        try {
            List<Customer> customers = accountsFacade.createCustomers();
            this.customers = customers;
            if (showCommandResult)
                commandResult = (customers != null) ? "Customers database generated successfully" : "Error occured while generating customers into database. Error logged.";
        } catch (Exception ex) {
            LOGGER.error("generateCustomers - Error is: ", ex);
            if (showCommandResult)
                commandResult = "Error: " + ex.getMessage();
        }
    }

    public void createNewAccount() {
        int id;
        double initialAmount;

        if (selectedCustomerId == null || selectedCustomerId.isEmpty()) {
            commandResult = "Customer Id must be entered!";
            return;
        }
        if (selectedInitialAmount == null || selectedInitialAmount.isEmpty()) {
            commandResult = "Initial amount must be entered!";
            return;
        }

        try {
            id = Integer.parseInt(selectedCustomerId);
        } catch (Exception e) {
            commandResult = "Id must be an integer!";
            return;
        }

        try {
            initialAmount = Double.parseDouble(selectedInitialAmount);
        } catch (Exception e) {
            commandResult = "Initial amount must be a number!";
            return;
        }

        commandResult = accountsFacade.createAccount(id, initialAmount);
        generateCustomers(false);
    }
}