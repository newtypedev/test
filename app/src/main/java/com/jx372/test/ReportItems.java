package com.jx372.test;

import java.util.UUID;

/**
 * Created by pys on 2017. 9. 13..
 */

public class ReportItems {

    //private  ReportItems sReportItems;
    private String reportNo="";

    private  String salesAccount = "";
    private  String targetFigure = "";
    private  String achiveRate = "";
    private  String startDistance = "";
    private  String endDistance = "";
    private  String driveDis = "";
    private  String content = "";
    private  String opinion = "";
    private  String title="";
    private  String approval="";
    private String startGauge="";
    private String endGauge = "";
    private String mileleage="";
    private String userid="";
    private String date="";
    private  UUID id;

    public ReportItems(String reportNo,String title,String reportSale,String content,String achiveRank,String approval,String opinion,String goalSale
    ,String startGauge,String endGauge,String mile,String userid,String date){
        this.reportNo = reportNo;
        this.id = UUID.randomUUID();
        this.title = title;
        this.salesAccount = reportSale;
        this.content = content;
        this.achiveRate = achiveRank;
        this.approval = approval;
        this.opinion = opinion;
        this.targetFigure = goalSale;
        this.startGauge = startGauge;
        this.endGauge = endGauge;
        this.mileleage = mile;
        this.userid = userid;
        this.date = date;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartGauge() {
        return startGauge;
    }

    public void setStartGauge(String startGauge) {
        this.startGauge = startGauge;
    }

    public String getEndGauge() {
        return endGauge;
    }

    public void setEndGauge(String endGauge) {
        this.endGauge = endGauge;
    }

    public String getMileleage() {
        return mileleage;
    }

    public void setMileleage(String mileleage) {
        this.mileleage = mileleage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ReportItems(){
        id = UUID.randomUUID();
        // date = new Date();

    }

    public  UUID getId() {
        return id;
    }


    //    public static ReportItems get() {
//
//        if (sReportItems == null) {
//            sReportItems = new ReportItems();
//
//        }
//
//        return sReportItems;
//    }


    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(String salesAccount) {
        this.salesAccount = salesAccount;
    }

    public String getTargetFigure() {
        return targetFigure;
    }

    public void setTargetFigure(String targetFigure) {
        this.targetFigure = targetFigure;
    }

    public String getAchiveRate() {
        return achiveRate;
    }

    public void setAchiveRate(String achiveRate) {
        this.achiveRate = achiveRate;
    }

    public String getStartDistance() {
        return startDistance;
    }

    public void setStartDistance(String startDistance) {
        this.startDistance = startDistance;
    }

    public String getEndDistance() {
        return endDistance;
    }

    public void setEndDistance(String endDistance) {
        this.endDistance = endDistance;
    }

    public String getDriveDis() {
        return driveDis;
    }

    public void setDriveDis(String driveDis) {
        this.driveDis = driveDis;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }
}