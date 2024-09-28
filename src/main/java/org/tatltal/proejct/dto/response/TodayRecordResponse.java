package org.tatltal.proejct.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TodayRecordResponse {

    @Schema(description = "오늘 마신 잔 수")
    private int todayCount;

    @Schema(description = "목표 잔 수 ")
    private int targetCount;

    @Schema(description = "설명 ")
    private String description;

    @Schema(description = "애니메이션 json?")
    private String imageJson;

}
