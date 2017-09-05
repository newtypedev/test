package com.jx372.test;


import java.util.ArrayList;

public class DayItems {


    private static DayItems sDayItems;
    private static String goalsale="0";
    private static String content="일일내용";
    private static String challenge="";

    public static void initData(){
        goalsale="";
        content="";
        challenge="";

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
