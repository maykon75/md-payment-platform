package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request;

import java.math.BigDecimal;

public record DepositRequestDTO(Long id, BigDecimal value) {
}
