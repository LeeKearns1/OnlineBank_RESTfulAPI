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
public class Transaction {
    private int transactionID;
    private String transactionType;
    private String date;
    private String transactionDescription;
    private Double currentBalance;
    private Double newBalance;
    private int transactionTo;
    private int transactionFrom;
    private long amount;
    
   public Transaction(){
       
   }
   
   public Transaction(int transactionID, String date, String transactionDescription, Double currentBalance){
       this.transactionID = transactionID;

       this.date = date;
       this.transactionDescription = transactionDescription;
       this.currentBalance = currentBalance;
       this.newBalance = newBalance;
   }
   
   public int getTransactionID(){
       return transactionID;
   }
   
   public String getTransactionType(){
       return transactionType;
   }
   
   public String getDate(){
       return date;
   }
   
   public String getTransactionDescription(){
       return transactionDescription;
   }
   
   public Double getCurrentBalance(){
       return currentBalance;
   }
   
   public Double getNewBalance(){
       return newBalance;
   }
   
   public void setDate(String date){
       this.date = date;
   }
   
   public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
   }
   
   public void setTransactionDescription(String transactionDescription){
       this.transactionDescription = transactionDescription;
   }
    
   public void setCurrentBalance(String currentBalance){
       this.transactionType = transactionType;
   }
    
   public void setTransactionType(String transactionType){
       this.transactionType = transactionType;
    }  
   
   public void setCurrentBalance(Double currentBalance){
       this.currentBalance = currentBalance;
   }
   
   public void setNewBalance(Double newBalance){
       this.newBalance = newBalance;
   }


    public int getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(int transactionTo) {
        this.transactionTo = transactionTo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getTransactionFrom() {
        return transactionFrom;
    }

    public void setTransactionFrom(int transactionFrom) {
        this.transactionFrom = transactionFrom;
    } 

}

