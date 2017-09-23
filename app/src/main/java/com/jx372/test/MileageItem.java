package com.jx372.test;

/**
 * Created by pys on 2017. 9. 22..
 */

public class MileageItem {

    private static MileageItem mileageItem;
    private static String startdis="0";
    private static String enddis="0";
    private static String startUrl;
    private static String endUrl;
    private static String mileage;


    public static MileageItem get() {

        if (mileageItem == null) {
            mileageItem = new MileageItem();

        }

        return mileageItem;
    }

    public static String getStartdis() {
        return startdis;
    }

    public static void setStartdis(String startdis) {
        MileageItem.startdis = startdis;
    }

    public static String getEnddis() {
        return enddis;
    }

    public static void setEnddis(String enddis) {
        MileageItem.enddis = enddis;
    }

    public static String getStartUrl() {
        return startUrl;
    }

    public static void setStartUrl(String startUrl) {
        MileageItem.startUrl = startUrl;
    }

    public static String getEndUrl() {
        return endUrl;
    }

    public static void setEndUrl(String endUrl) {
        MileageItem.endUrl = endUrl;
    }

    public static String getMileage() {
        return mileage;
    }

    public static void setMileage(String mileage) {
        MileageItem.mileage = mileage;
    }
}
