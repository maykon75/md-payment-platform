package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.handler.dto;

import java.util.List;

public record ValidationErrorDTO(List<String> errors){
}
