package org.mihai.controller;

import lombok.RequiredArgsConstructor;
import org.mihai.dto.request.OrderRequest;
import org.mihai.dto.response.OrderResponse;
import org.mihai.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/{username}")
    public OrderResponse saveOrders(@RequestBody OrderRequest orderRequest, @PathVariable String username) {
        return orderService.addOrderToUser(orderRequest, username);
    }


}
