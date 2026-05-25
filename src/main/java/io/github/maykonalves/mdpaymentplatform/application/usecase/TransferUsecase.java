package io.github.maykonalves.mdpaymentplatform.application.usecase;

import io.github.maykonalves.mdpaymentplatform.application.exception.InsufficientBalanceException;
import io.github.maykonalves.mdpaymentplatform.application.exception.InvalidUserTransferException;
import io.github.maykonalves.mdpaymentplatform.application.exception.InvalidValueException;
import io.github.maykonalves.mdpaymentplatform.application.port.input.ITransferInputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IAuthorizationOutputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.INotificationOutputPort;
import io.github.maykonalves.mdpaymentplatform.application.port.output.ITransferOutputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserBalance;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserType;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.TransferRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferUsecase implements ITransferInputPort {

    private static final Logger log = LoggerFactory.getLogger(TransferUsecase.class);

    private final ITransferOutputPort transferOutputPort;

    private final IAuthorizationOutputPort authorizationOutputPort;

    private final INotificationOutputPort notificationOutputPort;

    public TransferUsecase(final ITransferOutputPort transferOutputPort, final IAuthorizationOutputPort authorizationOutputPort, final INotificationOutputPort notificationOutputPort) {
        this.transferOutputPort = transferOutputPort;
        this.authorizationOutputPort = authorizationOutputPort;
        this.notificationOutputPort = notificationOutputPort;
    }

    @Override
    @Transactional
    public void tranfer(TransferRequest transferRequest) {

        if (transferRequest.value().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidValueException("Transfer value must be greater than zero");
        }

        UserBalance payer = transferOutputPort.checkPayer(transferRequest.payer());
        UserBalance payee = transferOutputPort.checkPayee(transferRequest.payee());

        if(payer.getUserType() == UserType.MERCHANT){
            throw new InvalidUserTransferException("The payer is a merchant, therefore they are not allowed to transfer funds.");

        }
        if(payer.getBalance().subtract(transferRequest.value()).compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalanceException("Insufficient balance");
        }
        payer.setBalance(payer.getBalance().subtract(transferRequest.value()));
        payee.setBalance(payee.getBalance().add(transferRequest.value()));

        authorizationOutputPort.authorize();

        transferOutputPort.updateBalances(payer, payee);

        notificationOutputPort.notifyUser();
        log.info("######## SUCCESSFUL ########");

    }
}
