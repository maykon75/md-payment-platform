package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.InvalidValueException;
import io.github.maykonalves.mdpaymentplatform.application.exception.NotFoundException;
import io.github.maykonalves.mdpaymentplatform.application.port.input.IDepositInputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IDepositOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositUsecase implements IDepositInputPort {

    private final IDepositOutputPort depositOutputPort;

    public DepositUsecase(final IDepositOutputPort depositOutputPort) {
        this.depositOutputPort = depositOutputPort;
    }

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        if(depositRequest.balance().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidValueException("Value must be greater than zero");
        }
        return depositOutputPort.deposit(depositRequest);
    }
}
