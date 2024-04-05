package org.mihai.service;

import lombok.RequiredArgsConstructor;
import org.mihai.convertor.UserConvertor;
import org.mihai.dto.request.UserRequest;
import org.mihai.dto.response.PaginatedUserResponse;
import org.mihai.dto.response.UserResponse;
import org.mihai.error.exceptions.UserException;
import org.mihai.model.User;
import org.mihai.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse saveUser(UserRequest userRequest) {

        userRepository
                .findByUsername(userRequest.username())
                .ifPresent(x -> {
                    throw new UserException("a user with this username already exists");
                });

        var user = UserConvertor.convertFrom(userRequest);

        var dbUser = userRepository.save(user);

        return UserConvertor.convertFrom(dbUser);
    }


    public PaginatedUserResponse getUsersPaginatedBad(Integer pageNr, Integer nrOFUsers) {

        List<UserResponse> users = new ArrayList<>();

        var pageWithUsers = PageRequest.of(pageNr, nrOFUsers);

        var findUsersPaginated = userRepository.getUsersPaginated(pageWithUsers);

        findUsersPaginated.forEach(user -> {
            var userResponse = UserConvertor.convertWithOrdersFrom(user);
            users.add(userResponse);

        });

        return new PaginatedUserResponse(findUsersPaginated.getTotalElements(), users);

    }


    public PaginatedUserResponse getUsersPaginatedGood(Integer pageNr, Integer nrOFUsers) {

        List<UserResponse> users = new ArrayList<>();

        var pageWithUsers = PageRequest.of(pageNr, nrOFUsers);

        var getUsersIds = userRepository.getUsersIdPaginated(pageWithUsers);

        var getUsersCount = userRepository.getUsersCount();

        var findUsersPaginated = userRepository.getUsersPaginated(getUsersIds);

        findUsersPaginated.forEach(user -> {
            var userResponse = UserConvertor.convertWithOrdersFrom(user);
            users.add(userResponse);

        });

        return new PaginatedUserResponse(getUsersCount, users);

    }


    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }





}
