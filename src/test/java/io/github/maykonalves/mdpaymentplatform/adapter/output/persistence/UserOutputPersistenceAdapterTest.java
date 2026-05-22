package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.IUserOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;

import io.github.maykonalves.mdpaymentplatform.domain.model.UserType;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserOutputPersistenceAdapterTest {

    @Mock
    private IUserOutputMapper userOutputMapper;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserOutputPersistenceAdapter adapter;

    private UserRequest userRequest;

    private UserEntity userEntity;

    private UserResponse userResponse;

    @BeforeEach
    void setup() {

        userRequest = new UserRequest(
                "Test User",
                "12345678900",
                "test@gmail.com",
                "123456"
        );

        userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setFullName("Test User");
        userEntity.setCpfCnpj("12345678900");
        userEntity.setEmail("test@gmail.com");
        userEntity.setPassword("123456");
        userEntity.setUserType(UserType.COMMON);
        userEntity.setBalance(new BigDecimal("0.00"));

        userResponse = new UserResponse(
                1L,
                "Test User",
                "12345678900",
                "test@gmail.com",
                "common",
                new BigDecimal("0.00")
        );
    }

    @Test
    void shouldCreateUserSuccessfully() {

        when(userOutputMapper.toEntity(userRequest))
                .thenReturn(userEntity);

        when(userRepository.save(userEntity))
                .thenReturn(userEntity);

        when(userOutputMapper.toDomain(userEntity))
                .thenReturn(userResponse);

        UserResponse response = adapter.userCreate(userRequest);

        assertEquals(userResponse, response);

        verify(userRepository)
                .save(userEntity);
    }

    @Test
    void shouldReturnAllUsers() {

        List<UserEntity> entities = List.of(userEntity);

        List<UserResponse> responses = List.of(userResponse);

        when(userRepository.findAll())
                .thenReturn(entities);

        when(userOutputMapper.toListDomain(entities))
                .thenReturn(responses);

        List<UserResponse> result = adapter.getAllUser();

        assertEquals(1, result.size());

        assertEquals(responses, result);

        verify(userRepository)
                .findAll();
    }

    @Test
    void shouldReturnTrueWhenCpfCnpjExists() {

        when(userRepository.existsByCpfCnpj("12345678900"))
                .thenReturn(true);

        boolean result = adapter.existsByCpfCnpj("12345678900");

        assertTrue(result);

        verify(userRepository)
                .existsByCpfCnpj("12345678900");
    }

    @Test
    void shouldReturnTrueWhenEmailExists() {

        when(userRepository.existsByEmail("test@gmail.com"))
                .thenReturn(true);

        boolean result = adapter.existsByEmail("test@gmail.com");

        assertTrue(result);

        verify(userRepository)
                .existsByEmail("test@gmail.com");
    }
}