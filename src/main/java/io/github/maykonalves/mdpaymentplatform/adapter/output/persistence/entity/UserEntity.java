package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity;

import io.github.maykonalves.mdpaymentplatform.domain.model.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpfCnpj;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserTypeEntity userType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;
}
