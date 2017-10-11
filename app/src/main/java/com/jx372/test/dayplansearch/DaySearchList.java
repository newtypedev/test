package com.jx372.test.dayplansearch;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 10..
 */

public class DaySearchList {


    private static DaySearchList daySearchList;
    private List<DaySearch> daysearchs;


    public static DaySearchList get(Context context){
        if(daySearchList == null){
            daySearchList = new DaySearchList(context);
        }
        return daySearchList;
    }

    public void cleanList(){

        daysearchs.clear();
    }

    private DaySearchList(Context context){
        daysearchs = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            Customer customer = new Customer();
//            customer.setName("업무보고"+i);
//            customers.add(customer);
//        }
    }
    public List<DaySearch> getDaysearchs(){
        return daysearchs;
    }



    public DaySearch getDaysearchs(UUID id){
        for(DaySearch daySearch: daysearchs){
            if(daySearch.getId().equals(id)){
                return daySearch;
            }
        }
        return null;
    }

    public void addDaysearch(DaySearch d){

        // consult.setTitle(s);
        // consult.setSolved(b);
        daysearchs.add(d);
    }

}
