package org.tatltal.proejct.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tatltal.proejct.common.CustomResponse;
import org.tatltal.proejct.dto.request.ModifyUserRequest;
import org.tatltal.proejct.facade.UserFacadeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserFacadeService userFacadeService;

    @GetMapping("/check/nickname")
    @Operation(summary = "닉네임 중복 체크 ", description = "중복이면 result가 true , 중복이 아니면 false")
    public ResponseEntity<CustomResponse<Boolean>> checkNickname(@RequestParam String nickname) {

        return CustomResponse.okResponseEntity(userFacadeService.checkNickname(nickname));
    }

}

