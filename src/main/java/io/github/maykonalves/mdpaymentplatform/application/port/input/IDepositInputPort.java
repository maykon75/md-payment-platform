package io.github.maykonalves.mdpaymentplatform.application.port.input;

import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;

public interface IDepositInputPort {
    DepositResponse deposit(DepositRequest depositRequest);
}
