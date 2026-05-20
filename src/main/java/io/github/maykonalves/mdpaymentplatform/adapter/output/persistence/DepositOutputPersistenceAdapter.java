package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.IDepositOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.exception.NotFoundException;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IDepositOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DepositOutputPersistenceAdapter implements IDepositOutputPort {

    private final IDepositOutputMapper depositOutputMapper;

    private final IUserRepository userRepository;

    public DepositOutputPersistenceAdapter(final IDepositOutputMapper depositOutputMapper, final IUserRepository userRepository) {
        this.depositOutputMapper = depositOutputMapper;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public DepositResponse deposit(DepositRequest depositRequest) {
        UserEntity userEntity = userRepository.findById(depositRequest.id()).orElseThrow(() -> new NotFoundException("User not found"));

        userEntity.setBalance(userEntity.getBalance().add(depositRequest.balance()));

        userRepository.save(userEntity);
        return depositOutputMapper.toDomain(userEntity);
    }

}
