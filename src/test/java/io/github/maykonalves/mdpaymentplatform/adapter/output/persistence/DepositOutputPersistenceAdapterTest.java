package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserTypeEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.IDepositOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.exception.NotFoundException;

import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositOutputPersistenceAdapterTest {

    @Mock
    private IDepositOutputMapper depositOutputMapper;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private DepositOutputPersistenceAdapter adapter;

    private DepositRequest depositRequest;

    private UserEntity userEntity;

    private DepositResponse depositResponse;

    @BeforeEach
    void setup() {

        depositRequest = new DepositRequest(
                1L,
                new BigDecimal("100.00")
        );

        userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setFullName("Common User");
        userEntity.setCpfCnpj("12345678900");
        userEntity.setEmail("user@email.com");
        userEntity.setPassword("123456");
        userEntity.setUserType(UserTypeEntity.COMMON);
        userEntity.setBalance(new BigDecimal("50.00"));

        depositResponse = new DepositResponse(
                1L,
                new BigDecimal("150.00")
        );
    }

    @Test
    void shouldDepositSuccessfully() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(userEntity));

        when(depositOutputMapper.toDomain(userEntity))
                .thenReturn(depositResponse);

        DepositResponse response = adapter.deposit(depositRequest);

        assertEquals(
                new BigDecimal("150.00"),
                userEntity.getBalance()
        );

        assertEquals(depositResponse, response);

        verify(userRepository)
                .save(userEntity);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> adapter.deposit(depositRequest)
        );

        assertEquals(
                "User not found",
                exception.getMessage()
        );
    }
}