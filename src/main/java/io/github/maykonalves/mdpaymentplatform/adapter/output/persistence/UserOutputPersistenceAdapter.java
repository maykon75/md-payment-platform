package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.IUserOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IUserOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserOutputPersistenceAdapter implements IUserOutputPort {

    private final IUserOutputMapper userOutputMapper;

    private final IUserRepository userRepository;

    public UserOutputPersistenceAdapter(IUserOutputMapper userOutputMapper, IUserRepository userRepository) {
        this.userOutputMapper = userOutputMapper;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserResponse userCreate(UserRequest userRequest) {
        return userOutputMapper.toDomain(userRepository.save(userOutputMapper.toEntity(userRequest)));
    }

    @Override
    @Transactional
    public List<UserResponse> getAllUser() {
        return userOutputMapper.toListDomain(userRepository.findAll());
    }

    @Override
    @Transactional
    public boolean existsByCpfCnpj(String cpfCnpj) {
        return userRepository.existsByCpfCnpj(cpfCnpj);
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
