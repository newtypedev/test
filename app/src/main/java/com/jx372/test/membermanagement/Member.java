package com.jx372.test.membermanagement;

import java.util.UUID;

/**
 * Created by pys on 2017. 10. 6..
 */

public class Member {

    private UUID id;
    private String userId;
    private String name;
    private String grade;
    private String title;

    public Member(){
        id = UUID.randomUUID();
    }

    public Member(String userid,String name,String grade){
        this.id = UUID.randomUUID();
        this.userId = userid;
        this.name = name;
        this.grade = grade;

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
