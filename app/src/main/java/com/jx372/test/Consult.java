package com.jx372.test;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pys on 2017. 8. 31..
 */

public class Consult {

    private UUID id;
    private String title;
    private Date date;
    private boolean solved;

    public Consult(){
        id = UUID.randomUUID();
        date = new Date();

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public UUID getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
