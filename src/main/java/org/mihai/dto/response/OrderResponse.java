package org.mihai.dto.response;

public record OrderResponse(
        Integer id,
        String item,
        double price
) {
}
