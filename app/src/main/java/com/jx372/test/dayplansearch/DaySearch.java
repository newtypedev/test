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

    public DaySearch(String userId,String date,String challenge,String content,String goalsale,String visitPoint,String shortDistance,String opinion){
        id = UUID.randomUUID();

        this.userId = userId;
        this.date = date;
        this.challenge = challenge;
        this.goalsale = goalsale;
        this.visitPoint = visitPoint;
        this.shortDistance = shortDistance;
        this.content = content;
        this.opinion = opinion;

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoalsale() {
        return goalsale;
    }

    public void setGoalsale(String goalsale) {
        this.goalsale = goalsale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getShortDistance() {
        return shortDistance;
    }

    public void setShortDistance(String shortDistance) {
        this.shortDistance = shortDistance;
    }

    public String getVisitPoint() {
        return visitPoint;
    }

    public void setVisitPoint(String visitPoint) {
        this.visitPoint = visitPoint;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
