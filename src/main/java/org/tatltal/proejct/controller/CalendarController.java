package org.tatltal.proejct.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tatltal.proejct.common.CustomResponse;
import org.tatltal.proejct.dto.response.CalendarDayResponse;
import org.tatltal.proejct.dto.type.PoisonState;
import org.tatltal.proejct.facade.CalendarFacadeService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "달력 API", description = "달력 API")
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    private final CalendarFacadeService calendarFacadeService;


    @GetMapping("/month")
    @Operation(description = "1년간 모든 날짜에 대해 이력 조회",summary = "1년간 모든 날짜에 대해 이력 조회")
    public ResponseEntity<CustomResponse<Map<LocalDate, PoisonState>>> getMonthRecord(
            @RequestParam ObjectId userId
    ) {

        var monthRecord = calendarFacadeService.getMonthRecord(userId);

        return CustomResponse.okResponseEntity(monthRecord);
    }

    @GetMapping("/day")
    @Operation(description = "해당 특정 일자의 기록 조회",summary = "해당 특정 일자의 기록 조회")
    public ResponseEntity<CustomResponse<CalendarDayResponse>> getDayRecord
            (@RequestParam ObjectId userId,
             @RequestParam int year,
             @RequestParam int month,
             @RequestParam int day) {
        var response = calendarFacadeService.getDayRecord(userId, year, month, day);
        return CustomResponse.okResponseEntity(response);
    }
}
