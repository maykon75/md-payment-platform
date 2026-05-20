package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response;

import java.math.BigDecimal;

public record UserResponseDTO (Long id, String fullName, String cpfCnpj, String email, String userType,
                               String password, BigDecimal balance){
}
