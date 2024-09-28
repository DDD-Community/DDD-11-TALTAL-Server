package org.tatltal.proejct.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.tatltal.proejct.dto.domain.UserDomain;
import org.tatltal.proejct.dto.type.GenderType;
import org.tatltal.proejct.dto.type.PurposeType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @MongoId
    private ObjectId id;

    private String nickname;

    private BigDecimal height;

    private BigDecimal weight;

    private LocalDate birth;

    private PurposeType purpose;

    private int targetNum;
    private GenderType gender;

    private boolean notification;
    private String token;

    public UserEntity(String nickname,
                      BigDecimal height,
                      BigDecimal weight,
                      LocalDate birth,
                      PurposeType purpose,
                      int targetNum,
                      GenderType gender) {
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.birth = birth;
        this.purpose = purpose;
        this.targetNum = targetNum;
        this.gender = gender;
    }


    public UserEntity(UserDomain userDomain) {
        this(userDomain.getId(),
                userDomain.getNickname(),
                userDomain.getHeight(),
                userDomain.getWeight(),
                userDomain.getBirth(),
                userDomain.getPurpose(),
                userDomain.getTargetNum(),
                userDomain.getGender(),
                userDomain.isNotification(),
                userDomain.getToken()
        );
    }
}
