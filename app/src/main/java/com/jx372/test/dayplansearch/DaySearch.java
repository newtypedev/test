package com.jx372.test.dayplansearch;

import java.util.UUID;

/**
 * Created by pys on 2017. 10. 10..
 */

public class DaySearch {

    private UUID id;
    private String userId="";
    private String date="";
    private  String goalsale="";
    private  String content="";
    private  String challenge="";
    private  String shortDistance="";
    private  String visitPoint="";
    private  String opinion="";

    public UUID getId() {
        return id;
    }
    public DaySearch(){id = UUID.randomUUID();}

    public DaySearch(String userId,String date,String challenge,String content,String goalsale,String visitPoint,String shortDistance){
        id = UUID.randomUUID();

        this.userId = userId;
        this.date = date;
        this.challenge = challenge;
        this.goalsale = goalsale;
        this.visitPoint = visitPoint;
        this.shortDistance = shortDistance;

    }
}
