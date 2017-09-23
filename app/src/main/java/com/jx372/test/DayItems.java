package com.jx372.test;


import java.util.ArrayList;
import java.util.Map;

public class DayItems {


    private static Map spinnerMap;
    private static Map spinnerMap2;
    private static ArrayList<String> spinnerItem;
    private static DayItems sDayItems;
    private static String goalsale="0";
    private static String content="<p>content</p>";
    private static String challenge="도전 과제 없음";
    private static String shortDistance="";
    private static String visitPoint="";
    private static String title="일일계획";
    private static String opinion="";


    public static void initData(){
        goalsale="0";
        content="<p>content</p>";
        challenge="도전 과제 없음";
        shortDistance="";
        visitPoint="";
        //title="";

    }

    public static ArrayList<String> getSpinnerItem() {
        return spinnerItem;
    }

    public static String getOpinion() {
        return opinion;
    }

    public static void setOpinion(String opinion) {
        DayItems.opinion = opinion;
    }

    public static void setSpinnerItem(ArrayList<String> spinnerItem) {
        DayItems.spinnerItem = spinnerItem;
    }

    public static Map getSpinnerMap2() {
        return spinnerMap2;
    }

    public static void setSpinnerMap2(Map spinnerMap2) {
        DayItems.spinnerMap2 = spinnerMap2;
    }

    public static Map getSpinnerMap() {
        return spinnerMap;
    }

    public static void setSpinnerMap(Map spinnerMap) {
        DayItems.spinnerMap = spinnerMap;
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
