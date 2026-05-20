package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.DepositRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response.DepositResponseDTO;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.DepositRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDepositInputMapper {

    @Mapping(target = "balance", source = "value")
    DepositRequest toDomain(DepositRequestDTO depositRequestDTO);

    DepositResponseDTO toDto(DepositResponse depositResponse);

}
