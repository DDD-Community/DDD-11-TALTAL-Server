package org.tatltal.proejct.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tatltal.proejct.common.CustomResponse;
import org.tatltal.proejct.dto.domain.UserDomain;
import org.tatltal.proejct.dto.request.DailyRecordRequest;
import org.tatltal.proejct.dto.request.OnboardingRequest;
import org.tatltal.proejct.dto.response.TodayRecordResponse;
import org.tatltal.proejct.dto.type.GenderType;
import org.tatltal.proejct.facade.HomeFacadeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class HomeController {

    private final HomeFacadeService homeFacadeService;
    private static final int SHOT_PER_CAFFEINE= 150;


    @PostMapping("/onboarding")
    @Operation(summary = "온보딩 등록",description = "온보딩 등록")
    public ResponseEntity<CustomResponse<UserDomain>> completeOnboarding(@RequestBody OnboardingRequest onboardingRequest) {


        UserDomain onboarding = homeFacadeService.onboarding(onboardingRequest);
        return CustomResponse.okResponseEntity(onboarding
        );
    }

    @PostMapping("/record")
    @Operation(summary = "당일 기록 등록",description = "당일 기록 등록")
    public ResponseEntity<CustomResponse<TodayRecordResponse>> recordDaily(@RequestBody DailyRecordRequest request) {


        return CustomResponse.okResponseEntity(homeFacadeService.recordDaily(request));
    }

    @GetMapping("/today")
    @Operation(summary = "당일 기록 조회",description = "당일 기록 조회")
    public ResponseEntity<CustomResponse<TodayRecordResponse>> getTodayRecord(@RequestParam ObjectId userId) {

        return CustomResponse.okResponseEntity(homeFacadeService.getRecordDaily(userId));
    }

    @GetMapping("/recommend")
    @Operation(summary = "몸무게 , 성별 , 나이별 추천 카페인 섭취량 ",description = "몸무게 , 성별 , 나이별 추천 카페인 섭취량 ")
    public ResponseEntity<CustomResponse<Integer>> getRecommendAmount(
            @RequestParam GenderType gender,
            @RequestParam
                    @DateTimeFormat(pattern = "yyyyMMdd")
                    @JsonFormat(pattern = "yyyyMMdd")
                    @Schema(pattern = "yyyyMMdd",type = "string",example = "20240101")
            LocalDate birth,
            @RequestParam BigDecimal height,
            @RequestParam BigDecimal weight
            ){


        LocalDate now = LocalDate.now();
        int age = Period.between(birth, now).getYears();
        BigDecimal bigDecimal = calculateCaffeineIntake(
                gender,
                age,
                height,
                weight
        );
        int shot = Math.floorDiv(bigDecimal.intValue(), SHOT_PER_CAFFEINE);

        return CustomResponse.okResponseEntity(shot);

    }

    public static BigDecimal calculateCaffeineIntake(GenderType gender, int age, BigDecimal height, BigDecimal weight) {
        // 기본 일일 카페인 섭취 권장량 (mg)
        BigDecimal baseCaffeineLimit = new BigDecimal("400");

        // 성별에 따른 조정
        if (gender.equals(GenderType.FEMALE)) {
            baseCaffeineLimit = baseCaffeineLimit.multiply(new BigDecimal("0.9"));
        }

        // 연령에 따른 조정
        if (age <= 12) {
            baseCaffeineLimit = weight.multiply(new BigDecimal("2.5"));
        } else if (age <= 18) {
            baseCaffeineLimit = weight.multiply(new BigDecimal("3.0"));
        } else if (age < 65) {
            baseCaffeineLimit = weight.multiply(new BigDecimal("5.5"));
        } else {
            baseCaffeineLimit = baseCaffeineLimit.min(new BigDecimal("300"));
        }

        // 체중에 따른 조정 (1kg당 5-6mg)
        BigDecimal weightBasedLimit = weight.multiply(new BigDecimal("5.5"));

        // 최종 카페인 섭취 한도 (기본 권장량과 체중 기반 한도 중 더 낮은 값 사용)
        return baseCaffeineLimit.min(weightBasedLimit);
    }


    @Operation(description = "앱 처음 진입시 이력 남기기 위한 API",summary = "앱 처음 진입시 이력 남기기 위한 API")
    @GetMapping("/enter")
    public ResponseEntity<CustomResponse<Object>> enterFirstTime(
            @RequestParam ObjectId userId
    ) {

        homeFacadeService.enterFirstTime(userId);
        return CustomResponse.okResponseEntity("");
    }

}
