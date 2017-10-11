package com.jx372.test.customermanagement;

import java.util.UUID;

/**
 * Created by pys on 2017. 9. 29..
 */

public class Customer {
    private String code;
    private String name;
    private String manager;
    private String address;
    private UUID id;

    public Customer(){id = UUID.randomUUID();}

    public Customer(String code,String name,String manager,String address){
        id = UUID.randomUUID();
        this.code = code;
        this.name = name;
        this.manager = manager;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
