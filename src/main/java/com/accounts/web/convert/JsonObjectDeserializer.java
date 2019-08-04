package com.accounts.web.convert;

import com.accounts.web.model.Account;
import com.accounts.web.model.Customer;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonObjectDeserializer {
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
}
