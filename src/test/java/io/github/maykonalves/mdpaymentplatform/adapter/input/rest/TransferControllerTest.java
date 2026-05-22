package io.github.maykonalves.mdpaymentplatform.adapter.input.rest;


import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.TransferRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserTypeEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IAuthorizationOutputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.INotificationOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class TransferControllerTest {

    @MockitoBean
    private IAuthorizationOutputPort authorizationOutputPort;

    @MockitoBean
    private INotificationOutputPort notificationOutputPort;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserRepository userRepository;

    private UserEntity payer;

    private UserEntity payee;

    @BeforeEach
    void setup() {

        doNothing().when(authorizationOutputPort).authorize();

        doNothing().when(notificationOutputPort).notifyUser();

        payer = new UserEntity();
        payer.setFullName("test Transfer");
        payer.setCpfCnpj("99945678900");
        payer.setEmail("transfer@gmail.com");
        payer.setPassword("123456");
        payer.setUserType(UserTypeEntity.COMMON);
        payer.setBalance(new BigDecimal("100.00"));

        payee = new UserEntity();
        payee.setFullName("Merchant Transfer");
        payee.setCpfCnpj("88845678000112");
        payee.setEmail("merchanttransfer@gmail.com");
        payee.setPassword("123456");
        payee.setUserType(UserTypeEntity.COMMON);
        payee.setBalance(new BigDecimal("0.00"));

        payer = userRepository.save(payer);
        payee = userRepository.save(payee);

    }


    @Test
    void transfer() throws Exception {
        TransferRequestDTO request = new TransferRequestDTO(
                new BigDecimal("50.00"),
                payer.getId(),
                payee.getId()
        );

        mockMvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}