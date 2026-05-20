package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.UserRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response.UserResponseDTO;
import io.github.maykonalves.mdpaymentplatform.domain.model.request.UserRequest;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserInputMapper {

    @Mapping(target = "cpfCnpj", source = "cpfCnpj", qualifiedByName = "mapCpfCnpj")
    UserRequest userToDomain(UserRequestDTO userRequestDTO);

    UserResponseDTO userToDto(UserResponse userResponse);

    List<UserResponseDTO> toListDto(List<UserResponse> userResponseList);

    @Named("mapCpfCnpj")
    default String CpfCnpj(String cpfCnpj) {
        return cpfCnpj.replaceAll("\\D", "");
    }
}
