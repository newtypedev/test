package com.jx372.test.customermanagement;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 5..
 */

public class CustomerList {

    private static CustomerList customerList;
    private List<Customer> customers;


    public static CustomerList get(Context context){
        if(customerList == null){
            customerList = new CustomerList(context);
        }
        return customerList;
    }

    public void cleanList(){

        customers.clear();
    }

    private CustomerList(Context context){
        customers = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            Customer customer = new Customer();
//            customer.setName("업무보고"+i);
//            customers.add(customer);
//        }
    }
    public List<Customer> getCustomers(){
        return customers;
    }



    public Customer getCustomers(UUID id){
        for(Customer customer: customers){
            if(customer.getId().equals(id)){
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer c){

        // consult.setTitle(s);
        // consult.setSolved(b);
        customers.add(c);
    }



}
