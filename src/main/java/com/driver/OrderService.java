package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository=new OrderRepository();

    public  String addOrder(Order order){
        return orderRepository.addOrder(order);
    }

    public  String addPartner(String id){
      return orderRepository.addPartner(id);
    }
    public String addOrderPartnerPair(String orderId, String partnerId){
        return  orderRepository.addOrderPartnerPair(orderId,partnerId);

    }
    public  Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }
    public  DeliveryPartner getPartnerById(String id){
        return orderRepository.getPartnerById(id);
    }
    public  int getOrderCountByPartnerId(String id){
        return orderRepository.getOrderCountByPartnerId(id);
    }
    public List<String> getOrdersByPartnerId(String id){
        return  orderRepository.getOrdersByPartnerId(id);
    }
    public  List<String> getAllOrders(){
        return  orderRepository.getAllOrders();
    }
    public  int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String pid){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,pid);
    }
    public String getLastDeliveryTimeByPartnerId(String pid){
        return orderRepository.getLastDeliveryTimeByPartnerId(pid);
    }
    public  String deletePartnerById(String pid){
        return orderRepository.deletePartnerById(pid);
    }
    public  String deleteOrderById(String id){
        return orderRepository.deleteOrderById(id);
    }
}
