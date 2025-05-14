package com.ecommerce.motoecom.service;

import com.ecommerce.motoecom.payload.OrderDTO;

public class OrderServiceImpl implements  OrderService{
    @Override
    public OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage) {
        return null;
    }
}
