package org.mihai.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
         Integer id,
         String username,
         int age,
         AddressResponse address,
         List<OrderResponse> orders
) {
}
