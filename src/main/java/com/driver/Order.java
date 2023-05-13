package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order() {
    }

    public Order(String id, String deliveryTime) {
        this.id=id;
        int hh=Integer.parseInt(deliveryTime.substring(2));
        int mm=Integer.parseInt(deliveryTime.substring(4,6));
        this.deliveryTime=hh*60+mm;

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
