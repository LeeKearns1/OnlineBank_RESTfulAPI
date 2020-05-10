/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankonline.services;
import com.mycompany.bankonline.databases.Database;
import java.util.List;
import com.mycompany.bankonline.Model.Customer;
/**
 *
 * @author graha
 */
public class CustomerServices {
    // Initialise list to contain all Customers
    //public static List<Customer> list = new ArrayList<>();
    Database d = new Database();
    private List<Customer> list = d.getCustomersDB();
    
//    public CustomerServices(){
//    //Customer cust1 = new Customer (1, "John", "Doe", "123 O'Connell Street", "x1213333", "johndoe@gmail.com");
//    //Customer cust2 = new Customer (2, "Jane", "Doe", "123 Grafton Street", "x34289742", "janed@hotmail.com");
//    //Customer cust3 = new Customer (3, "Graham", "Lynch", "456 Mayor Square", "x74989742", "grahaml@hotmail.com");
//                    
//            //list.add(cust1);
//            //list.add(cust2);
//            //list.add(cust3);
//}
    
    // Get all Customers
    public List<Customer> getAllCustomers(){
        //System.out.println("test");
        return list;
    }
    
    // Create a new Customer
    public Customer createCustomer(Customer c){
        c.setCustUniqueID(list.size() + 1);
        list.add(c);
        
        return c;
    }
    
     // Get cutomer by id
    public Customer getCustomer(int customerID){
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getCustUniqueID();
            if(id == customerID){
                return list.get(i);
            }
        }
        return null;
    }
    
}

