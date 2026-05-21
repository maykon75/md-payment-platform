package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.domain.model.UserBalance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITransferOutputMapper {

    UserEntity toEntity(UserBalance userBalance);

    UserBalance toDomain(UserEntity userEntity);
}
