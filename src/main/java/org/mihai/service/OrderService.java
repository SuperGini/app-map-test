package org.mihai.service;

import lombok.RequiredArgsConstructor;
import org.mihai.convertor.OrderConvertor;
import org.mihai.dto.request.OrderRequest;
import org.mihai.dto.response.OrderResponse;
import org.mihai.error.exceptions.UserException;
import org.mihai.error.exceptions.UserNotFoundException;
import org.mihai.repository.OrderRepository;
import org.mihai.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderResponse addOrderToUser(OrderRequest request, String username) {

       return userRepository.findByUsername(username)
                .map(x -> {
                    var order = OrderConvertor.convertFrom(request, x);
                    var orderDb = orderRepository.save(order);
                    return OrderConvertor.convertFrom(orderDb);
                })
                .orElseThrow(() -> new UserNotFoundException("username not found"));
    }


}
