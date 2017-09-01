package com.jx372.test;

import java.util.ArrayList;

/**
 * Created by Dell on 2017-08-19.
 */

public class WeekItems {

    private static WeekItems sWeekItems;
    private static ArrayList<String> MonItems;
    private static ArrayList<String> TueItems;
    private static ArrayList<String> WedItems;
    private static ArrayList<String> ThuItems;
    private static ArrayList<String> FriItems;
    private static String firstDate="0";
    private static String MonSales="0";
    private static String TueSales="0";
    private static String WedSales="0";
    private static String ThuSales="0";
    private static String FriSales="0";
    private static String targetFigure="0";
    private static String weekSale="0";
    private static String achiveRank="0";


    public static void initData(){
        MonItems = new ArrayList<>();
        TueItems = new ArrayList<>();
        WedItems = new ArrayList<>();
        ThuItems = new ArrayList<>();
        FriItems = new ArrayList<>();
        firstDate="0";
        MonSales = "0";
        TueSales = "0";
        WedSales = "0";
        ThuSales = "0";
        FriSales = "0";
        targetFigure = "0";
        weekSale = "0";
        achiveRank = "0";

    }

    public static WeekItems get() {

        if (sWeekItems == null) {
            sWeekItems = new WeekItems();
            MonItems = new ArrayList<>();
            TueItems = new ArrayList<>();
            WedItems = new ArrayList<>();
            ThuItems = new ArrayList<>();
            FriItems = new ArrayList<>();
        }

        return sWeekItems;
    }


    public static String getTargetFigure() {
        return targetFigure;
    }

    public static void setTargetFigure(String targetFigure) {
        WeekItems.targetFigure = targetFigure;
    }

    public static String getWeekSale() {
        return weekSale;
    }

    public static void setWeekSale(String weekSale) {
        WeekItems.weekSale = weekSale;
    }

    public static String getAchiveRank() {
        return achiveRank;
    }

    public static void setAchiveRank(String achiveRank) {
        WeekItems.achiveRank = achiveRank;
    }

    public static String getFirstDate() {
        return firstDate;
    }

    public static void setFirstDate(String firstDate) {
        WeekItems.firstDate = firstDate;
    }

    public ArrayList<String> getMonItems() {
        return MonItems;
    }

    public void setMonItems(ArrayList<String> monItems) {
        MonItems = monItems;
    }

    public ArrayList<String> getTueItems() {
        return TueItems;
    }

    public void setTueItems(ArrayList<String> tueItems) {
        TueItems = tueItems;
    }

    public ArrayList<String> getWedItems() {
        return WedItems;
    }

    public void setWedItems(ArrayList<String> wedItems) {
        WedItems = wedItems;
    }

    public ArrayList<String> getThuItems() {
        return ThuItems;
    }

    public void setThuItems(ArrayList<String> thuItems) {
        ThuItems = thuItems;
    }

    public ArrayList<String> getFriItems() {
        return FriItems;
    }

    public void setFriItems(ArrayList<String> friItems) {
        FriItems = friItems;
    }

    public static String getMonSales() {
        return MonSales;
    }

    public static void setMonSales(String monSales) {
        MonSales = monSales;
    }

    public static String getTueSales() {
        return TueSales;
    }

    public static void setTueSales(String tueSales) {
        TueSales = tueSales;
    }

    public static String getWedSales() {
        return WedSales;
    }

    public static void setWedSales(String wedSales) {
        WedSales = wedSales;
    }

    public static String getThuSales() {
        return ThuSales;
    }

    public static void setThuSales(String thuSales) {
        ThuSales = thuSales;
    }

    public static String getFriSales() {
        return FriSales;
    }

    public static void setFriSales(String friSales) {
        FriSales = friSales;
    }
}
