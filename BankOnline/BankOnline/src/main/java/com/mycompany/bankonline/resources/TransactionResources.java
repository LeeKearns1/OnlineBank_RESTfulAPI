/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.resources;
import java.util.* ;
import com.mycompany.bankonline.Model.Transaction;
import com.mycompany.bankonline.services.TransactionServices;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author graha
 */
@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResources {    
    // Create a new instance of TransactionService
    private TransactionServices transactionService = new TransactionServices();
    
    @GET
    @Path("/all")
    public List<Transaction> showAllTransactions(@PathParam("accountID")int id){
        return transactionService.getAllTransactions();
    }
    
     @GET
     @Path("/{transactionID}")
    public Transaction showCustomerTransactions(@PathParam("transactionID") int id){
        return transactionService.getTransactionsByID(id);
    }
   //transaction to do  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getTransactionByAccount(@PathParam("accountId") int accountNo){
        System.out.println("Success: Got transaction by account number");
        return transactionService.getTransactionByAccount(accountNo);
    }
    
    
    @POST
    @Path("/withdrawal")
//    http://localhost:49000/api/transactions/withdrawal?account=14343&amount=50
    //http://localhost:49000/api/accounts/{transactionId}/transactions
    public Response Withdrawal(@PathParam("accountID") int accountID, Transaction t) {
        transactionService.withdrawal(accountID,t);
        return Response.status(200).entity("Withdrawal Succesfully!").build();
    }
}




