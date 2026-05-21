package io.github.maykonalves.mdpaymentplatform.domain.model;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBalance {
    private Long id;
    private String fullName;
    private String email;
    private String cpfCnpj;
    private String password;
    private UserType userType;
    private BigDecimal balance;
}
