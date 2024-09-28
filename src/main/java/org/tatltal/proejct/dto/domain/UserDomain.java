package org.tatltal.proejct.dto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;
import org.tatltal.proejct.dto.request.ModifyUserRequest;
import org.tatltal.proejct.dto.type.GenderType;
import org.tatltal.proejct.dto.type.PurposeType;
import org.tatltal.proejct.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDomain {

    private ObjectId id;

    private String nickname;

    private BigDecimal height;

    private BigDecimal weight;

    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate birth;

    private PurposeType purpose;

    private int targetNum;
    private GenderType gender;
    private boolean notification;
    private String token;

    public UserDomain(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.nickname = userEntity.getNickname();
        this.height = userEntity.getHeight();
        this.weight = userEntity.getWeight();
        this.birth = userEntity.getBirth();
        this.purpose = userEntity.getPurpose();
        this.targetNum = userEntity.getTargetNum();
        this.gender = userEntity.getGender();
    }

    public void modifyUser(ModifyUserRequest request) {
        this.gender = request.getGender();
        this.birth = request.getBirth();
        this.weight = request.getWeight();
        this.height = request.getHeight();
    }
}
