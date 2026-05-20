package io.github.maykonalves.mdpaymentplatform.application.port.output;

import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;

public interface IDepositOutputPort {
    DepositResponse deposit(DepositRequest depositRequest);
}
