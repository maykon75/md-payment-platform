package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.InsufficientBalanceException;
import io.github.maykonalves.mdpaymentplatform.application.exception.InvalidUserTransferException;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IAuthorizationOutputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.INotificationOutputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.ITransferOutputPort;

import io.github.maykonalves.mdpaymentplatform.domain.model.UserBalance;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserType;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.TransferRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferUsecaseTest {

    @Mock
    private ITransferOutputPort transferOutputPort;

    @Mock
    private IAuthorizationOutputPort authorizationOutputPort;

    @Mock
    private INotificationOutputPort notificationOutputPort;

    @InjectMocks
    private TransferUsecase transferUsecase;

    private TransferRequest transferRequest;

    private UserBalance payer;

    private UserBalance payee;

    @BeforeEach
    void setup() {

        transferRequest = new TransferRequest(
                new BigDecimal("50.00"),
                1L,
                2L
        );

        payer = new UserBalance(
                1L,
                "test",
                "payer@gmail.com",
                "12345678900",
                "123456",
                UserType.COMMON,
                new BigDecimal("100.00")
        );

        payee = new UserBalance(
                2L,
                "Merchant",
                "merchant@gmail.com",
                "12345678000199",
                "123456",
                UserType.MERCHANT,
                new BigDecimal("0.00")
        );
    }

    @Test
    void shouldTransferSuccessfully() {

        when(transferOutputPort.checkPayer(1L))
                .thenReturn(payer);

        when(transferOutputPort.checkPayee(2L))
                .thenReturn(payee);

        doNothing()
                .when(authorizationOutputPort)
                .authorize();

        doNothing()
                .when(notificationOutputPort)
                .notifyUser();

        transferUsecase.tranfer(transferRequest);

        assertEquals(
                new BigDecimal("50.00"),
                payer.getBalance()
        );

        assertEquals(
                new BigDecimal("50.00"),
                payee.getBalance()
        );

        verify(transferOutputPort)
                .updateBalances(payer, payee);

        verify(notificationOutputPort)
                .notifyUser();
    }

    @Test
    void shouldThrowExceptionWhenPayerIsMerchant() {

        payer.setUserType(UserType.MERCHANT);

        when(transferOutputPort.checkPayer(1L))
                .thenReturn(payer);

        when(transferOutputPort.checkPayee(2L))
                .thenReturn(payee);

        InvalidUserTransferException exception = assertThrows(
                InvalidUserTransferException.class,
                () -> transferUsecase.tranfer(transferRequest)
        );

        assertEquals(
                "The payer is a merchant, therefore they are not allowed to transfer funds.",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsInsufficient() {

        payer.setBalance(new BigDecimal("10.00"));

        when(transferOutputPort.checkPayer(1L))
                .thenReturn(payer);

        when(transferOutputPort.checkPayee(2L))
                .thenReturn(payee);

        InsufficientBalanceException exception = assertThrows(
                InsufficientBalanceException.class,
                () -> transferUsecase.tranfer(transferRequest)
        );

        assertEquals(
                "Insufficient balance",
                exception.getMessage()
        );
    }
}