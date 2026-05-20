package io.github.maykonalves.mdpaymentplatform.application.port.output;

import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;

import java.util.List;

public interface IUserOutputPort {
    UserResponse userCreate(UserRequest userRequest);

    List<UserResponse> getAllUser();

    boolean existsByCpfCnpj(String cpfCnpj);

    boolean existsByEmail(String email);
}
