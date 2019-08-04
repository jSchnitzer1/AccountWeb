package com.accounts.web.convert;

import com.accounts.web.http.ResponseBuilder;
import com.accounts.web.model.Account;
import com.accounts.web.model.Customer;
import org.apache.log4j.Logger;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class JsonObjectDeserializer {
    private static final Logger LOGGER = Logger.getLogger(ResponseBuilder.class.getName());
    public static List<Customer> jsonToCustomers(Reader reader) throws IOException, ParseException {
        List<Customer> customerAccounts = new ArrayList<>();
        JSONParser parser=new JSONParser();
        Object object = parser.parse(reader);

        JSONArray array = (JSONArray) object;
        array.forEach(ca -> {
            List<Account> accounts = new ArrayList<>();
            JSONObject jCustomerAccounts = (JSONObject) ca;

            JSONObject jCustomer = (JSONObject) jCustomerAccounts.get("customerDTO");
            Customer customer = new Customer((long) jCustomer.get("customerId"), (String) jCustomer.get("firstname"), (String)jCustomer.get("lastname"), null);

            JSONArray jAccounts = (JSONArray) jCustomerAccounts.get("accountDTOs");
            jAccounts.forEach(a -> {
                JSONObject jAccount = (JSONObject) a;
                Account account = new Account((long) jAccount.get("accountId"), (double) jAccount.get("balance"), false, customer);
                accounts.add(account);
            });
            customer.setAccounts(accounts);
            customerAccounts.add(customer);
        });

        return customerAccounts.size() > 0 ? customerAccounts : null;
    }

    public static String jsonToCreatedAccount(Reader reader) throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        Object object = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) object;
        try {
            Long accountId = (Long) jsonObject.get("accountId");
            Long customerId = (Long) jsonObject.get("customerId");
            return accountId != null ? "Account: #" + accountId + " is added to customer #" + customerId : "Customer id is not in database!";
        } catch (Exception ex) {
            LOGGER.error("Customer id is not in database!");
            return "Error: Customer id is not in database!";
        }
    }
}
