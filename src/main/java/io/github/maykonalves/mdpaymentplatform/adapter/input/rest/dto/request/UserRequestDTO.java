package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO (@NotBlank(message = "fullName is required") String fullName,
                              @NotBlank(message = "cpfCnpj is required") String cpfCnpj,
                              @NotBlank(message = "email is required") String email,
                              @NotBlank(message = "password is required") String password){
}
