package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response;

public record UserResponseDTO (Long id, String fullName, String cpfCnpj, String email, String userType){
}
