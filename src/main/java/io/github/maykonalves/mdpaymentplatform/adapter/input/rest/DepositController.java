package io.github.maykonalves.mdpaymentplatform.adapter.input.rest;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper.IDepositInputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.DepositRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response.DepositResponseDTO;
import io.github.maykonalves.mdpaymentplatform.application.port.input.IDepositInputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/deposit")
public class DepositController {

    private final IDepositInputPort depositInputPort;

    private final IDepositInputMapper depositInputMapper;

    public DepositController(final IDepositInputPort depositInputPort, final IDepositInputMapper depositInputMapper) {
        this.depositInputPort = depositInputPort;
        this.depositInputMapper = depositInputMapper;
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<DepositResponseDTO> deposit(@RequestBody DepositRequestDTO depositRequestDTO){
        DepositResponse depositResponse = depositInputPort.deposit(depositInputMapper.toDomain(depositRequestDTO));

        return ResponseEntity.ok().body(depositInputMapper.toDto(depositResponse));

    }
}
