/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.Model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
/**
 *
 * @author graha
 */
@XmlRootElement
public class Account {
    private int Id;
    private int accountID;
    private String sortCode;
    private int accountNumber;
    private Double currentBalance;
    
    private List<Transaction> transactions = new ArrayList<>();
   
public Account(){

}

public Account(int accountID, String sortCode, int accountNumber, Double currentBalance, List<Transaction> transactions){
    this.accountID = accountID;
    this.sortCode = sortCode;
    this.accountNumber = accountNumber;
    this.currentBalance = currentBalance;
    this.transactions = transactions;
}



public int getaccountID(){
    return accountID;
}

public String getSortCode(){
    return sortCode;
}

public int getAccountNumber(){
    return accountNumber;
}

public Double getCurrentBalance(){
    return currentBalance;
}

public void setaccountID(int accountID){
    this.accountID=accountID;
}

public void setSortCode(String sortCode){
    this.sortCode=sortCode;
}

public void setAccountNumber(int accountNumber){
    this.accountNumber= accountNumber;
}

 public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

public void setCurrentBalance(Double currentBalance){
    this.currentBalance=currentBalance;
}



}

