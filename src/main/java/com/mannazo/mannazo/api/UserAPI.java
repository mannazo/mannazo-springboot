package com.mannazo.mannazo.api;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "사용자 관련 API")
public interface UserAPI {

    @Operation(summary = "사용자 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 정보를 성공적으로 검색했습니다."), // 구현중
            @ApiResponse(responseCode = "401", description = "사용자 정보를 보는 것이 허용되지 않습니다."),  // 구현중
            @ApiResponse(responseCode = "403", description = "찾으려고 하는 사용자 정보에 접근하는 것은 금지되어 있습니다."), // 구현중
            @ApiResponse(responseCode = "404", description = "찾으려고 하는 사용자 정보는 존재하지 않습니다.") // 구현중
    })
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id);

    @Operation(summary = "사용자 정보 수정")
    // TODO: updateUser API Swagger 주석 추가 예정 (담당 : 박정현)
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user);


    @Operation(summary = "사용자 탙퇴", description = "사용자 ID를 받아 회원탈퇴를 진행합니다.")
    // TODO: deleteUser API Swagger 주석 추가 예정 (담당 : 박정현)
    public ResponseEntity<UserResponseDTO.Delete> deleteUser(@PathVariable UUID id);
}
