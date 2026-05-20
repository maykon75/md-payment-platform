package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response;

import java.math.BigDecimal;

public record DepositResponseDTO(Long id, BigDecimal balance) {
}
