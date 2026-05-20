package io.github.maykonalves.mdpaymentplatform.domain.model.request;

import java.math.BigDecimal;

public record DepositRequest(Long id, BigDecimal balance){
}
