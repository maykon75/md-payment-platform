package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.DuplicateResourceException;
import io.github.maykonalves.mdpaymentplatform.application.port.input.IUserInputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IUserOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.User;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserUsecase implements IUserInputPort {

    private final IUserOutputPort userOutputPort;

    public UserUsecase(IUserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public UserResponse userCreate(User user) {
        if(userOutputPort.existsByCpfCnpj(user.cpfCnpj())) {
            throw new DuplicateResourceException("CPF/CNPJ already registered.");
        }
        if(userOutputPort.existsByEmail(user.email())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        return userOutputPort.userCreate(user);
    }
}
