package com.jx372.test;

/**
 * Created by Dell on 2017-08-26.
 */

public class User {
    private static User user;
    private String id="test01";
    private String dept="영업 1팀";


    public static User get() {

        if (user == null) {
            user = new User();

        }

        return user;
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
