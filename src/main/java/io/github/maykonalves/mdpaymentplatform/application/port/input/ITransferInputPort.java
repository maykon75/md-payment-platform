package io.github.maykonalves.mdpaymentplatform.application.port.input;

import io.github.maykonalves.mdpaymentplatform.domain.model.request.TransferRequest;

public interface ITransferInputPort {

    void transfer(TransferRequest transferRequest);
}
