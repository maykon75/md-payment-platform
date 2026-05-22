package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.DuplicateResourceException;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IUserOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUsecaseTest {

    @Mock
    private IUserOutputPort userOutputPort;

    @InjectMocks
    private UserUsecase userUsecase;

    private UserRequest userRequest;

    private UserResponse userResponse;

    @BeforeEach
    void setup() {

        userRequest = new UserRequest(
                "test",
                "12345678900",
                "test@gmail.com",
                "123456"
        );

        userResponse = new UserResponse(
                1L,
                "test",
                "12345678900",
                "test@gmail.com",
                "123456",
                new BigDecimal("0.00")
        );
    }

    @Test
    void createUserSuccessfully() {

        when(userOutputPort.existsByCpfCnpj(userRequest.cpfCnpj()))
                .thenReturn(false);

        when(userOutputPort.existsByEmail(userRequest.email()))
                .thenReturn(false);

        when(userOutputPort.userCreate(userRequest))
                .thenReturn(userResponse);

        UserResponse response = userUsecase.userCreate(userRequest);

        assertEquals(userResponse, response);

        verify(userOutputPort)
                .userCreate(userRequest);
    }

    @Test
    void shouldThrowExceptionWhenCpfCnpjAlreadyExists() {

        when(userOutputPort.existsByCpfCnpj(userRequest.cpfCnpj()))
                .thenReturn(true);

        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> userUsecase.userCreate(userRequest)
        );

        assertEquals(
                "CPF/CNPJ already registered.",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        when(userOutputPort.existsByCpfCnpj(userRequest.cpfCnpj()))
                .thenReturn(false);

        when(userOutputPort.existsByEmail(userRequest.email()))
                .thenReturn(true);

        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> userUsecase.userCreate(userRequest)
        );

        assertEquals(
                "Email already registered.",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnAllUsers() {

        List<UserResponse> users = List.of(userResponse);

        when(userOutputPort.getAllUser())
                .thenReturn(users);

        List<UserResponse> response = userUsecase.getAllUser();

        assertEquals(1, response.size());

        assertEquals(users, response);

        verify(userOutputPort)
                .getAllUser();
    }
}