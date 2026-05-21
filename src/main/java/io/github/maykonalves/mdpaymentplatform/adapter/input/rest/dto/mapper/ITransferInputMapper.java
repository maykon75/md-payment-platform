package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.TransferRequestDTO;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.TransferRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITransferInputMapper {

    TransferRequest toDomain(TransferRequestDTO transferRequestDTO);

}
