package com.jx372.test.customermanagement;

import java.util.UUID;

/**
 * Created by pys on 2017. 9. 29..
 */

public class Customer {
    private String code;
    private String name;
    private String address;
    private String contact;
    private String managername;
    private String managercontact;
    private String manageremail;
    private String time;
    private String posX;
    private String posY;

    private UUID id;

    public Customer(){id = UUID.randomUUID();}

    public Customer(String code,String name,String manager,String address,String contact,String managercontact,String manageremail,String time,String posX,String posY){
        id = UUID.randomUUID();
        this.code = code;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.time = time;
        this.managername = manager;
        this.managercontact = managercontact;
        this.manageremail = manageremail;
        this.posX = posX;
        this.posY = posY;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getManagercontact() {
        return managercontact;
    }

    public void setManagercontact(String managercontact) {
        this.managercontact = managercontact;
    }

    public String getManageremail() {
        return manageremail;
    }

    public void setManageremail(String manageremail) {
        this.manageremail = manageremail;
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
        return managername;
    }

    public void setManager(String manager) {
        this.managername = manager;
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
