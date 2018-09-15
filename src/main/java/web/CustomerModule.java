/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAOInterface;
import domain.Customer;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author bauja773
 */
public class CustomerModule extends Jooby{

    public CustomerModule(CustomerDAOInterface dao) {
        get("/api/customers/:username", (req) -> {
            String username = req.param("username").value();
            final Customer customer = dao.getCustomer(username);
            if (customer == null) {
                throw new Err(Status.NOT_FOUND);
            } else {
                customer.setPassword(null);
                return customer;
            }
        });
        post("api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);            
            dao.save(customer);
            rsp.status(Status.CREATED);

        });
    }
}
