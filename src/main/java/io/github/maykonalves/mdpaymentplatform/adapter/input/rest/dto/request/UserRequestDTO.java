package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request;

public record UserRequestDTO (String fullName, String cpfCnpj, String email, String password){
}
