package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserTypeEntity;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserOutputMapper {
    @Mapping(target = "userType", source = "cpfCnpj", qualifiedByName = "mapUserType")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    UserEntity toEntity(UserRequest userRequest);

    UserResponse toDomain(UserEntity userEntity);

    List<UserResponse> toListDomain(List<UserEntity> userEntityList);

    @Named("mapUserType")
    default UserTypeEntity mapUserType(String cpfCnpj) {
        return cpfCnpj.length() == 14
                ? UserTypeEntity.MERCHANT
                : UserTypeEntity.COMMON;
    }
}
