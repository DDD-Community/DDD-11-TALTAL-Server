package org.tatltal.proejct.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.tatltal.proejct.dto.type.GenderType;
import org.tatltal.proejct.dto.type.PurposeType;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OnboardingRequest {

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "키")
    private BigDecimal height;

    @Schema(description = "몸무게")
    private BigDecimal weight;
    @Schema(description = "쓰는 목적 ")

    private PurposeType purpose;

    @Schema(description = "생일",pattern = "yyyyMMdd",type = "string",example = "20240101")
    @JsonFormat(pattern = "yyyyMMdd")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate birth;

    @Schema(description = "성별")
    private GenderType gender;
    @Schema(description = "목표 잔 수 ")
    private int targetNum;

}
