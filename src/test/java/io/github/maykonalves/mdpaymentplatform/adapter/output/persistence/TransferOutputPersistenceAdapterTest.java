package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.ITransferOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.exception.NotFoundException;

import io.github.maykonalves.mdpaymentplatform.domain.model.UserBalance;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferOutputPersistenceAdapterTest {

    @Mock
    private ITransferOutputMapper transferOutputMapper;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private TransferOutputPersistenceAdapter adapter;

    private UserEntity payerEntity;

    private UserEntity payeeEntity;

    private UserBalance payer;

    private UserBalance payee;

    @BeforeEach
    void setup() {

        payerEntity = new UserEntity();

        payerEntity.setId(1L);
        payerEntity.setFullName("Common User");
        payerEntity.setCpfCnpj("12345678900");
        payerEntity.setEmail("payer@email.com");
        payerEntity.setPassword("123456");
        payerEntity.setUserType(UserType.COMMON);
        payerEntity.setBalance(new BigDecimal("100.00"));

        payeeEntity = new UserEntity();

        payeeEntity.setId(2L);
        payeeEntity.setFullName("Merchant User");
        payeeEntity.setCpfCnpj("12345678000199");
        payeeEntity.setEmail("payee@email.com");
        payeeEntity.setPassword("123456");
        payeeEntity.setUserType(UserType.MERCHANT);
        payeeEntity.setBalance(new BigDecimal("50.00"));

        payer = new UserBalance(
                1L,
                "Common User",
                "payer@email.com",
                "12345678900",
                "123456",
                UserType.COMMON,
                new BigDecimal("100.00")
        );

        payee = new UserBalance(
                2L,
                "Merchant User",
                "payee@email.com",
                "12345678000199",
                "123456",
                UserType.MERCHANT,
                new BigDecimal("50.00")
        );
    }

    @Test
    void shouldReturnPayerSuccessfully() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(payerEntity));

        when(transferOutputMapper.toDomain(payerEntity))
                .thenReturn(payer);

        UserBalance response = adapter.checkPayer(1L);

        assertEquals(payer, response);

        verify(userRepository)
                .findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenPayerNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> adapter.checkPayer(1L)
        );

        assertEquals(
                "Payer not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnPayeeSuccessfully() {

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(payeeEntity));

        when(transferOutputMapper.toDomain(payeeEntity))
                .thenReturn(payee);

        UserBalance response = adapter.checkPayee(2L);

        assertEquals(payee, response);

        verify(userRepository)
                .findById(2L);
    }

    @Test
    void shouldThrowExceptionWhenPayeeNotFound() {

        when(userRepository.findById(2L))
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> adapter.checkPayee(2L)
        );

        assertEquals(
                "Payee not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldUpdateBalancesSuccessfully() {

        when(transferOutputMapper.toEntity(payer))
                .thenReturn(payerEntity);

        when(transferOutputMapper.toEntity(payee))
                .thenReturn(payeeEntity);

        adapter.updateBalances(payer, payee);

        verify(userRepository)
                .saveAll(List.of(payerEntity, payeeEntity));
    }
}