package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.DuplicateResourceException;
import io.github.maykonalves.mdpaymentplatform.application.port.input.IUserInputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IUserOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUsecase implements IUserInputPort {

    private final IUserOutputPort userOutputPort;

    public UserUsecase(IUserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    //Method for creating a user
    @Override
    public UserResponse userCreate(UserRequest userRequest) {
        //Verify if CPF/CNPJ exists
        if(userOutputPort.existsByCpfCnpj(userRequest.cpfCnpj())) {
            throw new DuplicateResourceException("CPF/CNPJ already registered.");
        }
        //Verifyif EMAIL exists
        if(userOutputPort.existsByEmail(userRequest.email())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        //Create user
        return userOutputPort.userCreate(userRequest);
    }

    //Return all users
    @Override
    public List<UserResponse> getAllUser() {
        return userOutputPort.getAllUser();
    }
}
