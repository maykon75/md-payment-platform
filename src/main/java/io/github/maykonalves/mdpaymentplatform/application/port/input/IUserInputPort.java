package io.github.maykonalves.mdpaymentplatform.application.port.input;

import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;

import java.util.List;

public interface IUserInputPort {

    UserResponse userCreate(UserRequest userRequest);

    List<UserResponse> getAllUser();
}
