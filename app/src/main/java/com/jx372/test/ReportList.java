package com.jx372.test;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 9. 22..
 */

public class ReportList {

    private static ReportList reportList;
    private List<ReportListItem> reports;

    public static ReportList get(Context context){
        if(reportList == null){
            reportList = new ReportList(context);
        }
        return reportList;
    }

    private ReportList(Context context){
        reports = new ArrayList<>();
        for(int i=0;i<10;i++){
            ReportListItem report = new ReportListItem();
            report.setTitle("상담일지"+i);
            report.setOpinion(i);
            report.setSolved(i%2 ==0 );
            reports.add(report);
        }
    }


    public void deleteItem(UUID id){

        for(ReportListItem report: reports){
            if(report.getId().equals(id)){
                reports.remove(report);
                Log.v("삭제", reports.size()+"");
                return;
            }
        }

    }


    public void addConsult(Consult c){
        ReportListItem report = new ReportListItem();
        // consult.setTitle(s);
        // consult.setSolved(b);
        reports.add(report);
    }

    public void addItem(String s,boolean b){
        ReportListItem report = new ReportListItem();
        report.setTitle(s);
        report.setSolved(b);
        reports.add(report);
    }

    public List<ReportListItem> getReports(){
        return reports;
    }

    public ReportListItem getReports(UUID id){
        for(ReportListItem report: reports){
            if(report.getId().equals(id)){
                return report;
            }
        }
        return null;
    }

}
