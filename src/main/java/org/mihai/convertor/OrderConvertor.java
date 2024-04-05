package org.mihai.convertor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mihai.dto.request.OrderRequest;
import org.mihai.dto.response.OrderResponse;
import org.mihai.model.Order;
import org.mihai.model.User;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConvertor {


    public static Order convertFrom(OrderRequest request, User user) {

        var order =  Order.builder()
                .user(user)
                .item(request.item())
                .price(request.price())
                .build();
        user.setOrders(List.of(order));

        return order;
    }

    public static OrderResponse convertFrom(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getItem(),
                order.getPrice()
        );

    }


}
