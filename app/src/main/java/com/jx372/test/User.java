package com.jx372.test;

/**
 * Created by Dell on 2017-08-26.
 */

public class User {
    private static User user;
    private String id="test01";
    private String dept="영업 1팀";
    private String tempDate="";


    public static User get() {

        if (user == null) {
            user = new User();

        }

        return user;
    }

    public String getTempDate() {
        return tempDate;
    }

    public void setTempDate(String tempDate) {
        this.tempDate = tempDate;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
