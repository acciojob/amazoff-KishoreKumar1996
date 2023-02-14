package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order)
    {
        this.orderRepository.addOrder(order);
    }

    public void addPartner(String id) {
        this.orderRepository.addPartner(id);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        this.orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    public Order getOrderById(String id) {
        return this.orderRepository.getOrderById(id);
    }

    public DeliveryPartner getPartnerById(String id) {
        return this.orderRepository.getPartnerById(id);
    }

    public Integer getOrderCountByPartnerId(String id) {
        return this.orderRepository.getOrderCountByPartnerId(id);
    }

    public List<String> getOrdersByPartnerId(String id) {
        return this.orderRepository.getOrdersByPartnerId(id);
    }

    public List<String> getAllOrders() {
        return this.orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        return this.orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {

        return this.orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return this.orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        this.orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        this.orderRepository.deleteOrderById(orderId);

    }


}
