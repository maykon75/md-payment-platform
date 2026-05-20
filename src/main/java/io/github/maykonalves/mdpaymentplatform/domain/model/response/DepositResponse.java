package io.github.maykonalves.mdpaymentplatform.domain.model.response;

import java.math.BigDecimal;

public record DepositResponse(Long id, BigDecimal balance) {
}
