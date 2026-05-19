package io.github.maykonalves.mdpaymentplatform.application.port.input;

import io.github.maykonalves.mdpaymentplatform.domain.model.User;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;

public interface IUserInputPort {

    UserResponse userCreate(User user);
}
