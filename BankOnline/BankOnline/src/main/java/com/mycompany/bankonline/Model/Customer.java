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
 * @author Eamon
 */
@XmlRootElement
public class Customer {
    private int custUniqueID;
    private String custFName;
    private String custLName;
    private String custAddress;
    private String custPPSN;
    private String custEmail;
    
     private List<Account> accounts = new ArrayList<>();
    
    
    public Customer(int custUniqueID, String custFName, String custLName, String custAddress, String custPPSN, String custEmail, List<Account> accounts ) {
        
        this.custUniqueID = custUniqueID;
        this.custFName = custFName;
        this.custLName = custLName;
        this.custAddress = custAddress;
        this.custPPSN = custPPSN;
        this.custEmail = custEmail;
        this.accounts = accounts;
    }

    public Customer() {
    }
    
    

    public int getCustUniqueID() {
        return custUniqueID;
    }
    
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setCustUniqueID(int custUniqueID) {
        this.custUniqueID = custUniqueID;
    }

    public String getCustFName() {
        return custFName;
    }

    public void setCustFName(String custFName) {
        this.custFName = custFName;
    }

    public String getCustLName() {
        return custLName;
    }

    public void setCustLName(String custLName) {
        this.custLName = custLName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPPSN() {
        return custPPSN;
    }

    public void setCustPPSN(String custPPSN) {
        this.custPPSN = custPPSN;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
    
    public void createAccount(Account account){
        this.accounts.add(account);
    }
}
