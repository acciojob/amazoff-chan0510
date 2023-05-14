package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository {
    HashMap<String, Order> orderDb= new HashMap<>();
    HashMap<String, DeliveryPartner> partnerDb= new HashMap<>();
    HashMap<String, List<Order>> orderPartnerDb= new HashMap<>();
    public  String addOrder(Order order){
        orderDb.put(order.getId(),order);
        return  "success";
    }

    public  String addPartner(String id){
        DeliveryPartner deliveryPartner=new DeliveryPartner(id);
        partnerDb.put(deliveryPartner.getId(),deliveryPartner);
        return  "success";
    }
    public String addOrderPartnerPair(String orderId, String partnerId){
        DeliveryPartner deliveryPartner=partnerDb.get(partnerId);
        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
        partnerDb.put(partnerId,deliveryPartner);
        if(orderPartnerDb.containsKey(partnerId)){
            orderPartnerDb.get(partnerId).add(orderDb.get(orderId));
        }
        else{
            List<Order> list=new ArrayList<>();
            list.add(orderDb.get(orderId));
            orderPartnerDb.put(partnerId,list);
        }

        return  " success";
    }
    public  Order getOrderById(String id){
        return orderDb.get(id);
    }
    public  DeliveryPartner getPartnerById(String id){
        return partnerDb.get(id);
    }

    public  int getOrderCountByPartnerId(String id){
        return partnerDb.get(id).getNumberOfOrders();
    }
    public List<String> getOrdersByPartnerId(String id){
        List<Order> list = orderPartnerDb.get(id);
        List<String> result=new ArrayList<>();
        for(Order order: list){
            result.add(order.getId());
        }
        return  result;
    }
    public  List<String> getAllOrders(){
        List<String> result = new ArrayList<>();
        for(Map.Entry<String,Order> entry:orderDb.entrySet()){
            result.add(entry.getValue().getId());
        }
        return  result;
    }
    public  int getCountOfUnassignedOrders(){
        int count=0;
        List<Order> list = new ArrayList<>();
        for(Map.Entry<String,List<Order>> entry:orderPartnerDb.entrySet()){
            list.addAll(entry.getValue());
        }
        for(Map.Entry<String,Order> entry:orderDb.entrySet()){
            if(!list.contains(entry.getValue())){
                count++;
            }
        }
        return  count;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String pid){
        List<Integer> list=new ArrayList<>();
        int hh=Integer.parseInt(time.substring(2));
        int mm=Integer.parseInt(time.substring(4,6));
        int tt=hh*60+mm;
        int count=0;
        for(Map.Entry<String,List<Order>> entry: orderPartnerDb.entrySet()){
            for(Order order: entry.getValue()){
                list.add(order.getDeliveryTime());
            }
        }
        for(int x: list){
            if(x>tt)count++;
        }
        return  count;
    }
    public String getLastDeliveryTimeByPartnerId(String pid){
        List<Order> list =orderPartnerDb.get(pid);
        int max=0;
        for(Order order:list){
            max=(max<order.getDeliveryTime())?order.getDeliveryTime():max;
        }
        int hh=max/60;
        int mm=max%60;
        String result="";
        if(hh<10){
            result+=0+hh+":"+mm;
            return result;
        }
        return  hh+":"+mm;
    }
    public  String deletePartnerById(String pid){
        partnerDb.remove(pid);
        orderPartnerDb.remove(pid);
        return  "";
    }
    public  String deleteOrderById(String id){
        orderDb.remove(id);
        for(Map.Entry<String,List<Order>> entry:orderPartnerDb.entrySet()){
            for(Order order: entry.getValue()){
                if(order.getId().equals(id))entry.getValue().remove(order);
            }
        }
        return "";
    }
}
