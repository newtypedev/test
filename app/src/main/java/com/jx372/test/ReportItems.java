package com.jx372.test;

/**
 * Created by pys on 2017. 9. 13..
 */

public class ReportItems {

    private static ReportItems sReportItems;
    private static String salesAccount = "";
    private static String targetFigure = "";
    private static String achiveRate = "";
    private static String startDistance = "";
    private static String endDistance = "";
    private static String driveDis = "";
    private static String content = "";
    private static String opinion = "";


    public static ReportItems get() {

        if (sReportItems == null) {
            sReportItems = new ReportItems();

        }

        return sReportItems;
    }

    public static String getSalesAccount() {
        return salesAccount;
    }

    public static void setSalesAccount(String salesAccount) {
        ReportItems.salesAccount = salesAccount;
    }

    public static String getTargetFigure() {
        return targetFigure;
    }

    public static void setTargetFigure(String targetFigure) {
        ReportItems.targetFigure = targetFigure;
    }

    public static String getAchiveRate() {
        return achiveRate;
    }

    public static void setAchiveRate(String achiveRate) {
        ReportItems.achiveRate = achiveRate;
    }

    public static String getStartDistance() {
        return startDistance;
    }

    public static void setStartDistance(String startDistance) {
        ReportItems.startDistance = startDistance;
    }

    public static String getEndDistance() {
        return endDistance;
    }

    public static void setEndDistance(String endDistance) {
        ReportItems.endDistance = endDistance;
    }

    public static String getDriveDis() {
        return driveDis;
    }

    public static void setDriveDis(String driveDis) {
        ReportItems.driveDis = driveDis;
    }

    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        ReportItems.content = content;
    }

    public static String getOpinion() {
        return opinion;
    }

    public static void setOpinion(String opinion) {
        ReportItems.opinion = opinion;
    }
}