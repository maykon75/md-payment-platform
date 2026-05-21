package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper.ITransferOutputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository.IUserRepository;
import io.github.maykonalves.mdpaymentplatform.application.exception.NotFoundException;
import io.github.maykonalves.mdpaymentplatform.application.port.output.ITransferOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserBalance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferOutputPersistenceAdapter implements ITransferOutputPort {

    private final ITransferOutputMapper transferOutputMapper;

    private final IUserRepository userRepository;

    public TransferOutputPersistenceAdapter(final ITransferOutputMapper transferOutputMapper, final IUserRepository userRepository) {
        this.transferOutputMapper = transferOutputMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserBalance checkPayer(Long payer) {
        UserEntity userEntity = userRepository.findById(payer).orElseThrow(() -> new NotFoundException("Payer not found"));
        return transferOutputMapper.toDomain(userEntity);
    }

    @Override
    public UserBalance checkPayee(Long payee) {
        UserEntity userEntity = userRepository.findById(payee).orElseThrow(() -> new NotFoundException("Payee not found"));
        return transferOutputMapper.toDomain(userEntity);
    }

    @Override
    public void updateBalances(UserBalance payer, UserBalance payee) {
        userRepository.saveAll(
                List.of(transferOutputMapper.toEntity(payer), transferOutputMapper.toEntity(payee))
        );
    }

}
