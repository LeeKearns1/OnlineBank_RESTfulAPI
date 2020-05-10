/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.services;
import com.mycompany.bankonline.databases.Database;
import com.mycompany.bankonline.Model.Transaction;
import com.mycompany.bankonline.Model.Customer;
import com.mycompany.bankonline.Model.Account;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author graha
 */
public class TransactionServices {
    // A list to contain all transactions
    //public static List<Transaction> list = new ArrayList<>();
    Database d = new Database();
    AccountServices as = new AccountServices();
    private List<Transaction> list = d.getTransactionsDB();
    
    public TransactionServices(){
          //Transaction trans1 = new Transaction(1, "Debit", "12/11/19", "Rent", 1200.16, 700.16);
          //Transaction trans2 = new Transaction(2, "Debit", "13/11/19", "Coffee", 500.32, 498.38);
          //Transaction trans3 = new Transaction(2, "Debit", "14/11/19", "Gym", 500.32, 450.38);  
            
          //list.add(trans1);
           //list.add(trans2);
            //list.add(trans3);
    }
     // Getter method to retrieve all transactions
    public List<Transaction> getAllTransactions(){
        return list;
    }
    
        // Get account by id
    public Transaction getTransactionsByID(int transactionID){
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getTransactionID();
            if (id == transactionID) {
                return list.get(i);
            }
        }
        return null;
    }
 
    public void withdrawal(int accountID, Transaction t) {
        Date date = new Date();
        //Transaction newTrasaction = new Transaction(accountID,date.toString(),desc, amount);
        as.makeWithdrawal(accountID, t);
        //list.add(newTrasaction);
    }
    
    
           /* Get transactions by account number and month*/
    /*
    public ArrayList<Transaction> getTransactionsByAcc(int accNo, int month){
        ArrayList <Transaction> statement =  new ArrayList<>();
        
        for (Transaction t: db.getTransactionTB()) {
            if((t.getTransactionFrom() == (accNo)) || (t.getTransactionTo() == (accNo))){
                
                Date td = t.getTransactionDate();
                LocalDate ldtd = td.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int m = ldtd.getMonthValue();
                
                if(m == month){
                    statement.add(t);
                }
            }
        }
        return statement;
    }
    */
    
    public List<Transaction> getTransactionByAccount(int accountNo){
        
        Account a = as.getAccount(accountNo);
        List<Transaction> list = a.getTransactions();
        
        return list;
        //ArrayList<Transaction> list = new ArrayList<>();
        /*
        for (Transaction t :d.getTransactionsDB()){
            if((t.getTransactionFrom() == (accountNo)) || (t.getTransactionTo() ==(accountNo))){
                list.add(t);
            }
        }
        return list;*/
    }

    

}
