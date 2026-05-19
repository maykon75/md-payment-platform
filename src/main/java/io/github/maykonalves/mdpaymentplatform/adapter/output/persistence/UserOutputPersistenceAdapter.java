package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.IUserOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IUserOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.User;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserOutputPersistenceAdapter implements IUserOutputPort {

    private final IUserOutputMapper userOutputMapper;

    private final IUserRepository userRepository;

    public UserOutputPersistenceAdapter(IUserOutputMapper userOutputMapper, IUserRepository userRepository) {
        this.userOutputMapper = userOutputMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse userCreate(User user) {
        return userOutputMapper.toDomain(userRepository.save(userOutputMapper.toEntity(user)));
    }

    @Override
    public boolean existsByCpfCnpj(String cpfCnpj) {
        return userRepository.existsByCpfCnpj(cpfCnpj);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
