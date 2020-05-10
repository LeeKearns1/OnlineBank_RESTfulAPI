/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.resources;

import com.mycompany.bankonline.Model.Account;
import com.mycompany.bankonline.services.CustomerServices;
import com.mycompany.bankonline.Model.Customer;
import com.mycompany.bankonline.databases.Database;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResources {
    
    private CustomerServices customerService = new CustomerServices();
    
    @GET
    @Path("/all")
    public List<Customer> getAllCustomers(){
        //return customerService.getAllCustomers();
        return customerService.getAllCustomers();
    }
    
    @GET
    @Path("/{customerID}")
    public Customer getCustomer(@PathParam("customerID") int custId) {
        return customerService.getCustomer(custId);
        
    }
    
    @Path("/{customerID}/accounts")
    public AccountResources getAccountsResources() {
        System.out.println("Getting comments subresources...");
        return new AccountResources();
    }

    
    @POST
    public Customer createCustomer(Customer c) {
        return customerService.createCustomer(c);
    }
    

}
