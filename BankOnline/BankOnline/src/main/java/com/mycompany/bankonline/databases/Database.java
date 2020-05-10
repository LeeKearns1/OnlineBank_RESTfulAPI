/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.databases;
import com.mycompany.bankonline.Model.Account;
import com.mycompany.bankonline.Model.Customer;
import com.mycompany.bankonline.Model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class Database {
    
    public  static List<Customer> customerDB = new ArrayList<>();
    public static List<Account> accountDB = new ArrayList<>();
    public static List<Transaction> transactionDB = new ArrayList<>();
    public static boolean init = true;
    
    public Database() {
        if(init) {
            //add default data here!!!!
            //adding transactions
            Transaction t1 = new Transaction(1,"crisps","2019-01-01",200.00);
            Transaction t2 = new Transaction(2,"dinner","2019-03-01",190.00);
            Transaction t3 = new Transaction(3,"chips","2019-04-01",240.00);
            transactionDB.add(t1);
            transactionDB.add(t2);
            transactionDB.add(t3);
            //adding account which has three transactions above
            Account a1 =  new Account(1,"240-30-46",3467,200.00,transactionDB);
            accountDB.add(a1);
            //adding Customer which has one Account made above
            Customer c1 = new Customer(1,"Joe","Soaps","123 Pearse Street","863130K","joes@email.com",accountDB);
            customerDB.add(c1);

            init = false; //to prevent data being duplicated if database called again
        }
    }
    
  
    
    public List<Customer> getCustomersDB() {
        return customerDB;
    }
    
    public List<Account> getAccountsDB() {
        return accountDB;
    }
    
    public List<Transaction> getTransactionsDB() {
        return transactionDB;
    }
    
  
    
}
