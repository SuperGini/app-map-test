package org.mihai.error;

public record ErrorResponse(
        String message,
        int status

) {
}
