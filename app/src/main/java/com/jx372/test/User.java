package com.jx372.test;

import java.util.ArrayList;

/**
 * Created by Dell on 2017-08-26.
 */

public class User {
    private static User user;
    private String id;


    public static User get() {

        if (user == null) {
            user = new User();

        }

        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
