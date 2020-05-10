/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.services;

import com.mycompany.bankonline.databases.Database;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.bankonline.Model.Account;
import com.mycompany.bankonline.Model.Customer;
import com.mycompany.bankonline.Model.Transaction;
import static com.mycompany.bankonline.databases.Database.accountDB;
import static com.mycompany.bankonline.databases.Database.customerDB;
import static com.mycompany.bankonline.databases.Database.transactionDB;
import java.util.Date;
/**
 *
 * @author graham
 */
public class AccountServices {
    // List to contain all accounts
    //public List<Account> list = new ArrayList<>();
    Database d = new Database();
    private List<Account> list = d.getAccountsDB();
    public static List<Transaction> tlist = new ArrayList<>();
    Date date = new Date();
    
    
    public AccountServices(){
         //Account acc1 = new Account (1,"12-34-56", 101, 1200.34);
         //Account acc2 = new Account (2,"18-64-96", 640, 1280.56);
         //Account acc3 = new Account (3,"12-34-56", 140, 1300.89);
         
        //list.add(acc1);
        //list.add(acc2);
        //list.add(acc3);
        
    }
    //Gets all accounts
     public List<Account> getAllAccounts() {
        return list;
    }
     
      public void makeWithdrawal(int accountId, Transaction t) {
        Account temp;
        temp = list.get(accountId);
        System.out.println("Account ID=  "+accountId);
        temp.getTransactions().add(t);
          /*
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (accountId == temp.getAccountNumber()) {
                Account updatedAccount = new Account(temp.getAccountNumber(), temp.getSortCode(), temp.getAccountNumber(), temp.getCurrentBalance() - newBalance, tlist);
                list.add(i, updatedAccount); 
                break;
            }
        }*/
    }
     
      // Get account by id
    public Account getAccount(int accountID){
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getaccountID();
            if (id == accountID) {
                return list.get(i);
            }
        }
        return null;
    }
    
     
     // A customer can hold one or more accounts. Gets all acounts for customer by ID
    public List<Account> getAllAccountsForCustomer(int custId) {
        System.out.println("Getting accounts for customer "+custId);
        CustomerServices customerServices = new CustomerServices();
        Customer customer = customerServices.getCustomer(custId);
        System.out.println(custId);
        return customer.getAccounts();
    }
    
    
    // Balance - The customer can request a balance on any account at any time. Here we are getting the account balance based on the ID
    public Account getAccountBalance(int accId){
        AccountServices accountServices = new AccountServices();
        
        Account account = accountServices.getAccount(accId);
        // JSON kept returning 0 for balance, account number and account ID so setting values to be current values
        Account balance = new Account();
        balance.setCurrentBalance(account.getCurrentBalance());
        balance.setAccountNumber(account.getAccountNumber());
        balance.setaccountID(account.getaccountID());
        balance.getCurrentBalance();
        
        return balance;
    }
    // Create - Customers should be able to create an account with the bank
    public Account createNewAccount(Account a, int custId){
        Account acc = new Account();
        CustomerServices cs = new CustomerServices();
        Customer c = cs.getCustomer(custId);
        
         // Customers are able to have multiple account accounts with the same bank so we create an account for an exisiting ID
        
        //customer.createAccount(account);
        // The list holds all accounts, after this account has been added the list increases in size by 1
        a.setaccountID(list.size() + 1);
        c.getAccounts().add(a);
        ///list.add(a);
        // Return the account
        return a;
    }
    // Customers should be able to lodge money in their account

    

    
      public Account withdrawOrLodge(int acc, Transaction t){
       Account a = getAccount(acc);
       if(t.getTransactionType().equals("credit")){
           a.setCurrentBalance(a.getCurrentBalance()+t.getAmount());
       } else if(t.getTransactionType().equals("debit")) {
           a.setCurrentBalance(a.getCurrentBalance()-t.getAmount());
       }
       a.getTransactions().add(t);
       
       return a;
    }
    
     public static boolean addTransaction(Transaction t){
       
        transactionDB.add(t);
        return true;
    }
    public static boolean lodge(int acc, int amount){

        for(Account tempA : accountDB){  
            if(acc == tempA.getAccountNumber()){
                tempA.setCurrentBalance(tempA.getCurrentBalance() + amount);


                Transaction tempT = new Transaction();
                tempT.setCurrentBalance(tempA.getCurrentBalance());
                tempT.setAmount(amount);
                tempT.setDate("12/12/2019");
                tempT.setTransactionID(tempA.getTransactions().size()+1);
                tempT.setTransactionDescription("Lodgement");

                addTransaction(tempT);
                return true;
            }

        }
        return false;
    }
    
     public static boolean withdraw(int acc, int amount){
         
               
 int index = 0;
	        for(Account tempA : accountDB){  
	            if(acc == tempA.getAccountNumber()){
	                tempA.setCurrentBalance(tempA.getCurrentBalance() - amount);
	                accountDB.set(index, tempA);
	               
	                Transaction tempT = new Transaction();
	                tempT.setTransactionFrom(acc);
	                tempT.setAmount(amount);
	                //tempT.setMessage("Withdraw");
	               
	                addTransaction(tempT);
	                return true;
	            }
	            index++;
	        }
	        return false;
     }
     
     
public boolean transfer(int accFrom, int accTo, int amount){
	       
	        withdraw(accFrom, amount);
                lodge(accTo, amount);
                return true;
	    }
     
     
}
