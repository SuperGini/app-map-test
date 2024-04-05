package org.mihai.controller;

import lombok.RequiredArgsConstructor;
import org.mihai.dto.request.UserRequest;
import org.mihai.dto.response.PaginatedUserResponse;
import org.mihai.dto.response.UserResponse;
import org.mihai.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserResponse saveUser(@RequestBody UserRequest request) {
        return userService.saveUser(request);
    }


    @GetMapping("/users/{pageNr}/{nrOfUsers}")
    public PaginatedUserResponse getUsersPaginatedBad(@PathVariable Integer pageNr, @PathVariable Integer nrOfUsers) {
        return userService.getUsersPaginatedBad(pageNr, nrOfUsers);
    }


    @GetMapping("/usersx/{pageNr}/{nrOfUsers}")
    public PaginatedUserResponse getUsersPaginatedGood(@PathVariable Integer pageNr, @PathVariable Integer nrOfUsers) {
        return userService.getUsersPaginatedGood(pageNr, nrOfUsers);
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User deleted successfully");
    }


}
