package io.github.maykonalves.mdpaymentplatform.application.port.output;

import io.github.maykonalves.mdpaymentplatform.domain.model.User;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;

public interface IUserOutputPort {
    UserResponse userCreate(User user);

    boolean existsByCpfCnpj(String cpfCnpj);

    boolean existsByEmail(String email);
}
