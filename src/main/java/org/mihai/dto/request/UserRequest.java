package org.mihai.dto.request;

public record UserRequest(
        String username,
        int age,
        AddressRequest address
) {
}
