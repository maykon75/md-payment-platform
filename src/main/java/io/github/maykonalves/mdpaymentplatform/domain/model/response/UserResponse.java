package io.github.maykonalves.mdpaymentplatform.domain.model.response;

import java.math.BigDecimal;

public record UserResponse (Long id, String fullName, String cpfCnpj, String email, String userType,
                            String password, BigDecimal balance){
}
