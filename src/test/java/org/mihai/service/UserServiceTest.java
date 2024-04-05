package org.mihai.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mihai.dto.request.UserRequest;
import org.mihai.dto.response.AddressResponse;
import org.mihai.dto.response.UserResponse;
import org.mihai.error.exceptions.UserException;
import org.mihai.model.Address;
import org.mihai.model.User;
import org.mihai.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    UserRequest userRequest;
    User user;
    UserResponse expectedResponse;

    @BeforeEach
    public void setup() {
        // Initialize your objects here
        userRequest = new UserRequest("Mihai", 41, null);
        user = new User(1, "Mihai", 41, new Address(), null);
        expectedResponse = new UserResponse(1, "Mihai", 41, new AddressResponse(1, "", ""), null);
    }

    @Test
    void whenUserExists_thenThrowException() {
        // Assuming findByUsername(String username) is implemented to return an Optional<User>
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        assertThatExceptionOfType(UserException.class).isThrownBy(() -> userService.saveUser(userRequest));
    }

    @Test
    void whenUserDoesNotExist_thenSaveUser() {
        // Mock the behavior of the userRepository to simulate user saving
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = userService.saveUser(userRequest);

        // Validate the response
        assertThat(response).isEqualToComparingFieldByFieldRecursively(expectedResponse);

        // Verify userRepository interaction
        verify(userRepository).save(any(User.class));
    }

}
