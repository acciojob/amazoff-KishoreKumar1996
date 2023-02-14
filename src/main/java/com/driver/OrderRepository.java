package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {


    HashMap<String,Order> order_Id=new HashMap<>();
    HashMap<String,DeliveryPartner> delivery_Id=new HashMap<>();
    HashMap<String,String> assign_order=new HashMap<>();
    HashMap<String, List<String>> partner_order=new HashMap<>();

    public static String timeToString(Integer time) {
        return String.format("%0.2d:%0.2d", time/60, time%60);
    }
    public static int stringToTime(String time) {
        int h = Integer.parseInt(time.substring(0,2));
        int m = Integer.parseInt(time.substring(3));
        return (h*60)+m;
    }

    public String addOrder(Order order){
        order_Id.put(order.getId(), order);
        return "Successfully Created";
    }

    public String addPartner(String id){
        delivery_Id.put(id,new DeliveryPartner(id));
        return "successfully added";
    }

    public void addOrderPartnerPair(String orderID,String partnerID){
        assign_order.put(orderID, partnerID);
       if(!partner_order.containsKey(partnerID)){
           partner_order.put(partnerID,new ArrayList<>());
       }
       partner_order.get(partnerID).add(orderID);
    }

    public Order getOrderById(String orderId){
       return order_Id.get(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId) {
        return delivery_Id.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return partner_order.size();
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return partner_order.get(partnerId);
    }

    public List<String> getAllOrders() {
     List<String> list=new ArrayList<>();
     for(String st : order_Id.keySet()){
         list.add(st);
     }
     return list;
    }

    public int getCountOfUnassignedOrders(){
        return order_Id.size()- assign_order.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        int min= 0;

        for(String orderId : partner_order.get(partnerId)) {
            if(order_Id.get(orderId ).getDeliveryTime() > stringToTime(time)) min++;
        }

        return min;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int lastDeliveryOrder=0;
        for(String lastOrder : partner_order.get(partnerId)){

           Order time= order_Id.get(lastOrder);
            lastDeliveryOrder=Math.max(time.getDeliveryTime(),lastDeliveryOrder);
        }
        return timeToString(lastDeliveryOrder);
    }

    public void deletePartnerById(String partnerId){
        for(String obj: partner_order.get(partnerId)){
            Order s= order_Id.get(obj);
            assign_order.remove(s);
            order_Id.remove(s);
        }
        partner_order.remove(partnerId);
    }
    public void deleteOrderById(String orderId) {
        for(String i : assign_order.keySet()) {
            if(i.equals(orderId)) {
                assign_order.remove(i);
            }
        }

        for(String i : partner_order.keySet()) {
            for(String j : partner_order.get(i)) {
                if(j.equals(orderId))partner_order.get(i).remove(j);
            }
        }

        order_Id.remove(orderId);
    }

}
