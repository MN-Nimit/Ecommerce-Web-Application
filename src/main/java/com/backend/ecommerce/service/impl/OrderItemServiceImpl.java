package com.backend.ecommerce.service.impl;

import com.backend.ecommerce.entity.OrderItem;
import com.backend.ecommerce.repository.OrderItemRepository;
import com.backend.ecommerce.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    final private OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
