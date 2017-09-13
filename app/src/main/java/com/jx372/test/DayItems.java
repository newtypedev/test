package com.jx372.test;


import java.util.ArrayList;

public class DayItems {


    private static ArrayList<String> spinnerItem;
    private static DayItems sDayItems;
    private static String goalsale="0";
    private static String content="-";
    private static String challenge="";
    private static String shortDistance="";
    private static String visitPoint="";
    private static String title="제목";


    public static void initData(){
        goalsale="0";
        content="-";
        challenge="";
        shortDistance="";
        visitPoint="";
        //title="";

    }

    public static ArrayList<String> getSpinnerItem() {
        return spinnerItem;
    }

    public static void setSpinnerItem(ArrayList<String> spinnerItem) {
        DayItems.spinnerItem = spinnerItem;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        DayItems.title = title;
    }

    public String getShortDistance() {
        return shortDistance;
    }

    public void setShortDistance(String shortDistance) {
        this.shortDistance = shortDistance;
    }

    public String getVisitPoint() {
        return visitPoint;
    }

    public void setVisitPoint(String visitPoint) {
        this.visitPoint = visitPoint;
    }

    public static String getChallenge() {
        return challenge;
    }

    public static void setChallenge(String challenge) {
        DayItems.challenge = challenge;
    }

    public static String getGoalsale() {
        return goalsale;
    }

    public static void setGoalsale(String goalsale) {
        DayItems.goalsale = goalsale;
    }

    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        DayItems.content = content;
    }

    public static DayItems get() {

        if (sDayItems == null) {
            sDayItems = new DayItems();

        }

        return sDayItems;
    }



}
