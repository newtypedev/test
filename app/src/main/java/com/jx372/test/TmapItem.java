package com.jx372.test;

import java.util.ArrayList;

/**
 * Created by pys on 2017. 8. 30..
 */

public class TmapItem {

    private static TmapItem mTmapItem;
    private String shortDistance="";
    private String visitPoint="";

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

    public static TmapItem get() {

        if (mTmapItem == null) {
            mTmapItem = new TmapItem();

        }

        return mTmapItem;
    }

}
