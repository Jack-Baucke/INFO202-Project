/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import domain.Sale;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author bauja773
 */
public class SaleModule extends Jooby {

    public SaleModule(SaleDAO dao) {

        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
//            System.out.println(sale.toString());
//            System.out.println("usr: " + sale.getCustomer().getUsername());
//            System.out.println("email: " + sale.getCustomer().getEmailAddress());
//            System.out.println("firstname: " + sale.getCustomer().getFirstName());
//            System.out.println("surname: " + sale.getCustomer().getSurname());
//            System.out.println("credit " + sale.getCustomer().getCreditCardDetails());
//            System.out.println("ship addr: " + sale.getCustomer().getShippingAddress());
//            System.out.println("password: " + sale.getCustomer().getPassword());
            dao.save(sale);
            rsp.status(Status.CREATED);

            CompletableFuture.runAsync(() -> {
                try {
                    Email email = new SimpleEmail();
                    email.setHostName("localhost");
                    email.setSmtpPort(2525);
                    email.setFrom("productwebsite@gmail.com");
                    email.setSubject("Order Confirmation");
                    email.setMsg("Hi " + sale.getCustomer().getFirstName() + ",\n"
                            + "Thanks for shopping with us! You ordered: \n"
                            + sale.printItems() + "\n"
                                    + "This comes to a total of:\n"
                                    + "$" + sale.getTotal().toString());
                    email.addTo(sale.getCustomer().getEmailAddress());
                    email.send();
                } catch (EmailException ex) {
                    Logger.getLogger(SaleModule.class.getName()).log(Level.SEVERE, null, ex);
                }

        });

        });
    }
}
