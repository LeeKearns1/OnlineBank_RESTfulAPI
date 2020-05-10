package com.mycompany.bankonline.resources;
import com.mycompany.bankonline.services.AccountServices;
import com.mycompany.bankonline.Model.Account;
import com.mycompany.bankonline.Model.Customer;
import com.mycompany.bankonline.Model.Transaction;
import com.mycompany.bankonline.databases.Database;
import com.mycompany.bankonline.services.CustomerServices;
import com.mycompany.bankonline.services.TransactionServices;
import java.util.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author graha
 */

@Path("/accounts")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AccountResources {
   AccountServices accountService = new AccountServices();
   CustomerServices customerService = new CustomerServices();
   //Gets all accounts
    @GET
    public List<Account> getAllAccountsforCustomer(@PathParam("customerID") int custId) {
          return accountService.getAllAccountsForCustomer(custId);
    }
    
    @GET
    @Path("/all")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
            
    // Get account by ID
    @GET
    @Path("/{accountID}")
    public Account getByID(@PathParam("accountID") int accId){
        return accountService.getAccount(accId);
    }
    // Get balance by account ID
    @GET
    @Path("/{accountID}/balance")
    public Account getAccountBalance(@PathParam("accountID") int accId){
        return accountService.getAccountBalance(accId);
    }
    
    //Example: http://localhost:49000/api/accounts/withdraw/123/980
     @POST
    @Path("/{accountID}/withdraw")
    public void withdrawORLodge(@PathParam("accountID") int accNo, Transaction t){
        
        accountService.withdrawOrLodge(accNo, t);
    }
    
    @POST
    @Path("/lodge/{accNo}/{amount}")
    public void lodge(@PathParam("accNo") int accNo, @PathParam("amount") int amount){
        if(accountService.lodge(accNo, amount)){
            System.out.println("Lodged " + amount+" To: " + accNo);
        }
        else{
            System.out.println("FAIL: Lodgement unsuccessful");
        }
    }
     @POST
    @Path("/withdraw/{accNo}/{amount}")
    public void withdraw(@PathParam("accNo") int accNo, @PathParam("amount") int amount){
        if(accountService.withdraw(accNo, amount)){
            System.out.println("Withdrew " + amount+ " From: "+ accNo);
            
        } else {
            System.out.println("FAIL: Withdraw unsuccessful");
        }
    }
    
            @POST
	    @Path("/transfer/{accFrom}/{accTo}/{amount}")
	    public void transfer(@PathParam("accFrom") int accFrom, @PathParam("accTo") int accTo, @PathParam("amount") int amount){
	        if(accountService.transfer(accFrom, accTo, amount)){
	            System.out.println("Transfered " + amount+ " from "+ accFrom + " To" + accTo);
	        }
	        else{
	            System.out.println("FAIL: Transfer unsuccessful");
	        }
	    }

    
    
    
   
    
    @POST
    public Account createAccountforCustomer(Account a,@PathParam("customerID")int custId) {
        accountService.createNewAccount(a,custId);
        
        return a;
        
    }
    
    @Path("/{accountID}/transactions")
    public TransactionResources getTransactionResources() {
        return new TransactionResources();
    }


  
    
    
   
    
}