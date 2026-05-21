package io.github.maykonalves.mdpaymentplatform.adapter.input.rest;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper.ITransferInputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.TransferRequestDTO;
import io.github.maykonalves.mdpaymentplatform.application.port.input.ITransferInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final ITransferInputPort transferInputPort;

    private final ITransferInputMapper transferInputMapper;

    public TransferController(final ITransferInputPort transferInputPort, final ITransferInputMapper transferInputMapper) {
        this.transferInputPort = transferInputPort;
        this.transferInputMapper = transferInputMapper;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequestDTO transferRequestDTO){
        transferInputPort.tranfer(transferInputMapper.toDomain(transferRequestDTO));

        return ResponseEntity.ok().build();
    }
}
