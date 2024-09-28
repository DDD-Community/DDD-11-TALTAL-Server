package org.tatltal.proejct.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tatltal.proejct.common.CustomResponse;
import org.tatltal.proejct.dto.request.ModifyPushRequest;
import org.tatltal.proejct.dto.request.ModifyUserRequest;
import org.tatltal.proejct.facade.UserFacadeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MyPageController {

    private final UserFacadeService userFacadeService;

    @PatchMapping("/user")
    @Operation(description = "유저 정보 변경 ",summary = "유저 정보 변경")
    public ResponseEntity<CustomResponse<Object>> modifyUser(
            @RequestBody ModifyUserRequest request
    ) {
        userFacadeService.modifyUser(request);
        return CustomResponse.okResponseEntity(null);
    }

    @PatchMapping("/push")
    @Operation(description = "푸시 설정 변경", summary = "푸시 설정 변경")
    public void modifyPush(
            @RequestBody ModifyPushRequest request
    ) {
        userFacadeService.modifyPush(request);


    }
}
