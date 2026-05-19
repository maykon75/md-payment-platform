package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.UserRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response.UserResponseDTO;
import io.github.maykonalves.mdpaymentplatform.domain.model.User;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IUserInputMapper {

    @Mapping(target = "cpfCnpj", source = "cpfCnpj", qualifiedByName = "mapCpfCnpj")
    User userToDomain (UserRequestDTO userRequestDTO);

    UserResponseDTO userToDto (UserResponse userResponse);

    //UserDeposit userDepositToDomain (UserDepositRequestDTO userDepositRequestDTO);

    //UserDepositRequestDTO userDepositToDto (UserDeposit userDeposit);

    //UserTransfer userTransferToDomain (UserTransferDTO userTransferDTO);

    //UserTransferDTO userTransferToDomain (UserTransfer userTransfer);

    @Named("mapCpfCnpj")
    default String CpfCnpj(String cpfCnpj) {
        return cpfCnpj.replaceAll("\\D", "");
    }
}
