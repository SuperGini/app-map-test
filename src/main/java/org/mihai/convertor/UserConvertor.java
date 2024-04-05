package org.mihai.convertor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mihai.dto.request.UserRequest;
import org.mihai.dto.response.AddressResponse;
import org.mihai.dto.response.OrderResponse;
import org.mihai.dto.response.UserResponse;
import org.mihai.model.Address;
import org.mihai.model.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConvertor {

    public static User convertFrom(UserRequest request) {
//        var address = Address.builder()
//                .street(request.address().street())
//                .number(request.address().number())
//                .build();

        return User.builder()
                .age(request.age())
                .username(request.username())
//                .address(address)
                .build();

    }

    public static UserResponse convertFrom(User user) {
//        var address = new AddressResponse(
//                user.getAddress().getId(),
//                user.getAddress().getStreet(),
//                user.getAddress().getNumber()
//        );


        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .age(user.getAge())
//                .address(address)
                .build();
    }

    public static UserResponse convertWithNoAddressFrom(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .age(user.getAge())
                .build();
    }

    public static UserResponse convertWithOrdersFrom(User user) {
        List<OrderResponse> orders = new ArrayList<>();

        user.getOrders().forEach(order -> {
            var orderResponse = new OrderResponse(order.getId(), order.getItem(), order.getPrice());
            orders.add(orderResponse);

        });

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .age(user.getAge())
                .orders(orders)
                .build();


    }


}
