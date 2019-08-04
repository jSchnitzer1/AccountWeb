package com.accounts.web.controller;

import com.accounts.web.convert.JsonObjectDeserializer;
import com.accounts.web.http.ResponseBuilder;
import com.accounts.web.model.Customer;
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
        Reader reader = null;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            LOGGER.error("createCustomers - thread sleep error", e);
        }

        reader = ResponseBuilder.buildReponse("POST", "application/json", "/api/account/initCustomers");
        if (reader != null) {
            try {
                return JsonObjectDeserializer.jsonToCustomers(reader);
            } catch (IOException e) {
                LOGGER.error("checkAccountEndpoint - IOException in parsing returned customers json, error is: ", e);
            } catch (ParseException e) {
                LOGGER.error("checkAccountEndpoint - unable to parse returned customers json, error is: ", e);
            }
        }
        return null;
    }
}
