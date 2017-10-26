package com.jx372.test;

/**
 * Created by Dell on 2017-08-26.
 */

public class User {
    private static User user;
    private String id;
    private String dept;
    private String grade;
    private String email;
    private String name;
    private String tempDate="";
    private String today="";
    private String start="0";
    private int currentPager=0;


    public static User get() {

        if (user == null) {
            user = new User();

        }

        return user;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getToday() {
        return today;
    }

    public int getCurrentPager() {
        return currentPager;
    }

    public void setCurrentPager(int currentPager) {
        this.currentPager = currentPager;
    }

    public void setToday(String today) {
        this.today = today;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
