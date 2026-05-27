package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequestDTO(@NotNull(message = "id is required") Long id,
                                @NotNull(message = "value is required") BigDecimal value) {
}
