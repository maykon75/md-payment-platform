package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;

import io.github.maykonalves.mdpaymentplatform.domain.model.response.DepositResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDepositOutputMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "balance", source = "balance")
    DepositResponse toDomain(UserEntity userEntity);
}
