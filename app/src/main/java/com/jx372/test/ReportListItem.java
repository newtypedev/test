package com.jx372.test;

import java.util.UUID;

/**
 * Created by pys on 2017. 9. 22..
 */

public class ReportListItem {
    private UUID id;
    private boolean solved;
    private int opinion;
    private String title;

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public UUID getId() {
        return id;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReportListItem(){
        id = UUID.randomUUID();
       // date = new Date();

    }
//    public ReportListItem(String no,String title,String content,String name,String human_name ){
//        this.no = no;
//        this.title = title;
//        this.content = content;
//        this.name = name;
//        this.human_name = human_name;
//
//    }

}
