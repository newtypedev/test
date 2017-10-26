package com.jx372.test.parking;

/**
 * Created by pys on 2017. 10. 26..
 */

public class Parking {

    private String name;
    private String num;
    private String time;
    private String pay;

    public Parking(){


    }
    public Parking(String name,String num,String time,String pay ){

        this.name = name;
        this.num = num;
        this.time = time;
        this.pay = pay;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
