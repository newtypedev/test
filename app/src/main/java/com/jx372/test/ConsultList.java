package com.jx372.test;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 8. 31..
 */

public class ConsultList {

    private static ConsultList consultList;
    private List<Consult> consults;

    public static ConsultList get(Context context){
        if(consultList == null){
            consultList = new ConsultList(context);
        }
        return consultList;
    }

    private ConsultList(Context context){
        consults = new ArrayList<>();
        for(int i=0;i<10;i++){
            Consult consult = new Consult();
            consult.setTitle("상담일지"+i);
            consult.setSolved(i%2 ==0 );
            consults.add(consult);
        }
    }

    public void deleteItem(UUID id){

        for(Consult consult: consults){
            if(consult.getId().equals(id)){
                consults.remove(consult);
                Log.v("삭제", consults.size()+"");
                return;
            }
    }

    }

    public void addItem(String s,boolean b){
        Consult consult = new Consult();
        consult.setTitle(s);
        consult.setSolved(b);
        consults.add(consult);
    }

    public List<Consult> getConsults(){
        return consults;
    }

    public Consult getConsults(UUID id){
        for(Consult consult: consults){
            if(consult.getId().equals(id)){
                return consult;
            }
        }
        return null;
    }

}
