package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.InvalidValueException;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IDepositOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositUsecaseTest {

    @Mock
    private IDepositOutputPort depositOutputPort;

    @InjectMocks
    private DepositUsecase depositUsecase;

    private DepositRequest depositRequest;

    private DepositResponse depositResponse;

    @BeforeEach
    void setup() {

        depositRequest = new DepositRequest(
                1L,
                new BigDecimal("100.00")
        );

        depositResponse = new DepositResponse(
                1L,
                new BigDecimal("100.00")
        );
    }

    @Test
    void shouldDepositSuccessfully() {

        when(depositOutputPort.deposit(depositRequest))
                .thenReturn(depositResponse);

        DepositResponse response = depositUsecase.deposit(depositRequest);

        assertEquals(depositResponse, response);

        verify(depositOutputPort)
                .deposit(depositRequest);
    }

    @Test
    void shouldThrowExceptionWhenValueIsZero() {

        DepositRequest request = new DepositRequest(
                1L,
                BigDecimal.ZERO
        );

        InvalidValueException exception = assertThrows(
                InvalidValueException.class,
                () -> depositUsecase.deposit(request)
        );

        assertEquals(
                "Value must be greater than zero",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenValueIsNegative() {

        DepositRequest request = new DepositRequest(
                1L,
                new BigDecimal("-10.00")
        );

        InvalidValueException exception = assertThrows(
                InvalidValueException.class,
                () -> depositUsecase.deposit(request)
        );

        assertEquals(
                "Value must be greater than zero",
                exception.getMessage()
        );
    }
}