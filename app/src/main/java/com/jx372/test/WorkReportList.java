package com.jx372.test;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 9. 24..
 */

public class WorkReportList {

    private static WorkReportList reportList;
    private List<ReportItems> reports;
    private static int count=0;

    public static WorkReportList get(Context context){
        if(reportList == null){
            reportList = new WorkReportList(context);
        }
        return reportList;
    }
    public void cleanList(){

        count =0;
        reports.clear();
    }

    private WorkReportList(Context context){
        reports = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            ReportItems report = new ReportItems();
//            report.setTitle("업무보고"+i);
//            report.setApproval(i+"");
//            reports.add(report);
//        }
    }
    public List<ReportItems> getReports(){
        return reports;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        WorkReportList.count = count;
    }

    public ReportItems getReports(UUID id){
        for(ReportItems report: reports){
            if(report.getId().equals(id)){
                return report;
            }
        }
        return null;
    }



    public void addReport(ReportItems r){
       // ReportItems report = new ReportItems(r);
        // consult.setTitle(s);
        // consult.setSolved(b);
        reports.add(r);
    }





}
