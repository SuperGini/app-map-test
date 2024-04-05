package org.mihai.dto.response;

import java.util.List;

public record PaginatedUserResponse(
        Long totalUsers,
        List<UserResponse> users
) {


}
