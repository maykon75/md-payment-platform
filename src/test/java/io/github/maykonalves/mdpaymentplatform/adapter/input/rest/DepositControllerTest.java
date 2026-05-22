package io.github.maykonalves.mdpaymentplatform.adapter.input.rest;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.DepositRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.UserRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DepositControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void deposit() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO("Test Deposit", "55545678900",
                "deposit@email.com", "12345");
        DepositRequestDTO requestDTO = new DepositRequestDTO(1L, BigDecimal.valueOf(100L));

        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO)));

        mockMvc.perform(patch("/deposit").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.balance").value(BigDecimal.valueOf(100.0)));
    }
}