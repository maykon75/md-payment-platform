package io.github.maykonalves.mdpaymentplatform.domain.model;

public record User(String fullName, String cpfCnpj, String email, String password) {
}
