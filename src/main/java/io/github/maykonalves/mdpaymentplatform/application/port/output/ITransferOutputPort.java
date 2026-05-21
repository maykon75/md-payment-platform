package io.github.maykonalves.mdpaymentplatform.application.port.output;

import io.github.maykonalves.mdpaymentplatform.domain.model.UserBalance;

public interface ITransferOutputPort {

    UserBalance checkPayer(Long payer);

    UserBalance checkPayee(Long payee);

    void updateBalances(UserBalance payer, UserBalance payee);
}
