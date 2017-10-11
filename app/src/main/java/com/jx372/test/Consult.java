package com.jx372.test;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pys on 2017. 8. 31..
 */

public class Consult {

    private UUID id;
    private boolean solved;
    private String no;
    private String title;
    private Date date;

    private String content;
    private String manager;
    private String code;
    private String name;

    public Consult(){
        id = UUID.randomUUID();
        date = new Date();

    }
    public Consult(String no,String title,String content,String manager_name,String code,String name ){
        id = UUID.randomUUID();
        this.no = no;
        this.title = title;
        this.content = content;
        this.name = name;
        this.manager = manager_name;
        this.code = code;

    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
