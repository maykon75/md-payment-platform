package io.github.maykonalves.mdpaymentplatform.domain.model.request;

public record UserRequest(String fullName, String cpfCnpj, String email, String password) {
}
