package com.accounts.web.controller;

import com.accounts.web.convert.JsonObjectDeserializer;
import com.accounts.web.http.Response;
import com.accounts.web.http.ResponseBuilder;
import com.accounts.web.model.Customer;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Stateless
public class AccountsFacade {
    private static final Logger LOGGER = Logger.getLogger(AccountsFacade.class.getName());

    public AccountsFacade() {
    }

    public List<Customer> createCustomers() {
        LOGGER.info("checkAccountEndpoint is triggered");
        sleep200();

        Response response = ResponseBuilder.buildReponse("POST", "application/json", "/api/account/initCustomers");
        if (response != null) {
            try {
                return response.getResponseCode() < 299 ? JsonObjectDeserializer.jsonToCustomers(response.getReader()) : null;
            } catch (IOException e) {
                LOGGER.error("checkAccountEndpoint - IOException in parsing returned customers json, error is: ", e);
            } catch (ParseException e) {
                LOGGER.error("checkAccountEndpoint - ParseException: unable to parse returned customers json, error is: ", e);
            }
        }
        return null;
    }

    public String createAccount(int customerId, double initialAmount) {
        LOGGER.info("createAccount is triggered");
        Reader reader;
        sleep200();
        Response response = ResponseBuilder.buildReponse("POST", "application/json", "/api/account/createAccount/" + customerId + "/" + initialAmount);
        if(response != null) {
            try {
                return JsonObjectDeserializer.jsonToCreatedAccount(response.getReader());
            } catch (IOException e) {
                LOGGER.error("checkAccountEndpoint - IOException in parsing returned account json, error is: ", e);
            } catch (ParseException e) {
                LOGGER.error("checkAccountEndpoint - ParseException: unable to parse returned account json, error is: ", e);
            }
        }
        return "Error in service response!";
    }

    public String createTransaction(int customerId, double txAmount) {
        LOGGER.info("createTransaction is triggered");
        Reader reader;
        sleep200();
        Response response = ResponseBuilder.buildReponse("POST", "application/json", "/api/account/createTransaction/" + customerId + "/" + txAmount);
        if(response != null) {
            if(response.getResponseCode() < 299) {
                return "Transaction successfully created for customer: " + customerId;
            } else {
                try {
                    return IOUtils.toString(response.getReader());
                } catch (IOException e) {
                    return "Error in creating a new transaction";
                }
            }
        }
        return "Error in service response!";
    }

    private void sleep200() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            LOGGER.error("createCustomers - thread sleep error", e);
        }
    }

}
