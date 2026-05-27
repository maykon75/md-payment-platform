package io.github.maykonalves.mdpaymentplatform.adapter.input.rest;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.request.UserRequestDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.mapper.IUserInputMapper;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.dto.response.UserResponseDTO;
import io.github.maykonalves.mdpaymentplatform.application.port.input.IUserInputPort;
import io.github.maykonalves.mdpaymentplatform.domain.model.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserInputPort userInputPort;

    private final IUserInputMapper userInputMapper;

    public UserController(IUserInputPort userInputPort, IUserInputMapper userInputMapper) {
        this.userInputPort = userInputPort;
        this.userInputMapper = userInputMapper;
    }


    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponseDTO> userCreate(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponse userResponse = userInputPort.userCreate(userInputMapper.userToDomain(userRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(userInputMapper.userToDto(userResponse));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(userInputMapper.toListDto(userInputPort.getAllUser()));
    }
}
