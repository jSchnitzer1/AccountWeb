package com.accounts.web.view;

import com.accounts.web.controller.AccountsFacade;
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
    private String selectedAmount;
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

    public String getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(String selectedAmount) {
        this.selectedAmount = selectedAmount;
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
        if (isInvalidCustomerIdOrAmount()) return;

        Integer id = null;
        id = parseCustomerId();
        if(id == null) return;

        Double amount;
        amount = parseAmount();
        if(amount == null) return;

        commandResult = accountsFacade.createAccount(id, amount);
        generateCustomers(false);
    }


    public void sendNewTransaction() {
        if (isInvalidCustomerIdOrAmount()) return;

        Integer id = null;
        id = parseCustomerId();
        if(id == null) return;

        Double amount;
        amount = parseAmount();
        if(amount == null) return;
        commandResult = accountsFacade.createTransaction(id, amount);
        generateCustomers(false);
    }

    public String getDefaultAccount(boolean isDefault) {
        return (isDefault) ? "-Default Account-" : "-Not Default Account-";
    }

    private Integer parseCustomerId() {
        int id;
        try {
            id = Integer.parseInt(selectedCustomerId);
            return id;
        } catch (Exception e) {
            commandResult = "Id must be an integer!";
            return null;
        }
    }

    private Double parseAmount() {
        Double amount = null;
        try {
            amount = Double.parseDouble(selectedAmount);
            return amount;
        } catch (Exception e) {
            commandResult = "Initial amount must be a number!";
        }
        return null;
    }

    private boolean isInvalidCustomerIdOrAmount() {
        if (selectedCustomerId == null || selectedCustomerId.isEmpty()) {
            commandResult = "Customer Id must be entered!";
            return true;
        }
        if (selectedAmount == null || selectedAmount.isEmpty()) {
            commandResult = "Amount must be entered!";
            return true;
        }
        return false;
    }

}