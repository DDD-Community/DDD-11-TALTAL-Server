package org.tatltal.proejct.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.tatltal.proejct.dto.type.GenderType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserRequest {

    @Schema(description = "유저 id")
    private ObjectId userId;

    @Schema(description = "생일")
    private LocalDate birth;

    @Schema(description = "성별")
    private GenderType gender;

    @Schema(description = "키")
    private BigDecimal height;

    @Schema(description = "몸무게")
    private BigDecimal weight;

}
