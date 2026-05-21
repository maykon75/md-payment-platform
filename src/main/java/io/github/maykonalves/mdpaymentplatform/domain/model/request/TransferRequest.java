package io.github.maykonalves.mdpaymentplatform.domain.model.request;


import java.math.BigDecimal;

public record TransferRequest(BigDecimal value, Long payer, Long payee) {
}
