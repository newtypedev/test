package com.jx372.test;

/**
 * Created by pys on 2017. 10. 14..
 */

public class Statistic {
    private static Statistic statistic;
    private int[] sales;


    public static Statistic get() {

        if (statistic == null) {
            statistic = new Statistic();

        }

        return statistic;
    }

    public void initSaleList(){
        sales = new int[13];

        for(int i=0;i<sales.length;i++){
            sales[i] = 0;
        }
    }

    public int[] getSales() {
        return sales;
    }

    public void setSales(int[] sales) {
        this.sales = sales;
    }
}
