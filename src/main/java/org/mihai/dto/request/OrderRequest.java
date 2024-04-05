package org.mihai.dto.request;

public record OrderRequest(
        String item,
        double price
) {
}
