package org.tatltal.proejct.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tatltal.proejct.dto.domain.UserDomain;
import org.tatltal.proejct.dto.request.DailyRecordRequest;
import org.tatltal.proejct.dto.request.OnboardingRequest;
import org.tatltal.proejct.dto.response.TodayRecordResponse;
import org.tatltal.proejct.entity.UserEntity;
import org.tatltal.proejct.service.FortuneService;
import org.tatltal.proejct.service.RecordService;
import org.tatltal.proejct.service.UserHistoryService;
import org.tatltal.proejct.service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeFacadeService {

    private final UserService userService;
    private final RecordService recordService;
    private final UserHistoryService userHistoryService;
    private final FortuneService fortuneService;


    public UserDomain onboarding(OnboardingRequest request) {
        UserEntity userEntity = new UserEntity(
                request.getNickname(),
                request.getHeight(),
                request.getWeight(),
                request.getBirth(),
                request.getPurpose(),
                request.getTargetNum(),
                request.getGender()
        );
        return userService.save(userEntity);
    }

    public TodayRecordResponse recordDaily(DailyRecordRequest request) {

        ObjectId userId = request.getUserId();
        recordService.newRecord(userId);
        return getRecordDaily(userId);

    }

    public TodayRecordResponse getRecordDaily(ObjectId userId) {

        Integer todayRecord = recordService.getTodayRecord(userId);
        UserDomain user = userService.getUser(userId);
        int targetNum = user.getTargetNum();
        BigDecimal ratio = new BigDecimal(todayRecord).divide(BigDecimal.valueOf(targetNum), 10, RoundingMode.HALF_UP);

        return new TodayRecordResponse(
                todayRecord,
                targetNum,
                fortuneService.getRandomDescription(),
                getImageJson(ratio)
        );
    }

    public void enterFirstTime(ObjectId userId) {
        userHistoryService.updateFirstTime(userId);
    }

    private String getImageJson(BigDecimal ratio) {
        if (BigDecimal.ZERO.equals(ratio)) {
            return "poe_main_0.json";
        } else if (BigDecimal.ONE.divide(new BigDecimal("3"), 10, RoundingMode.HALF_UP).compareTo(ratio) > 0) {
            return "poe_main_1.json";
        } else if (BigDecimal.ONE.divide(new BigDecimal("2"), 10, RoundingMode.HALF_UP).compareTo(ratio) > 0) {
            return "poe_main_2.json";
        } else if (new BigDecimal("2").divide(new BigDecimal("3"), 10, RoundingMode.HALF_UP).compareTo(ratio) > 0) {
            return "poe_main_3.json";
        } else if (ratio.equals(BigDecimal.ONE)) {
            return "poe_main_4.json";
        } else if (ratio.compareTo(new BigDecimal("1.3")) < 1) {
            return "poe_main_5.json";
        } else if (ratio.compareTo(new BigDecimal("1.7")) < 1) {
            return "poe_main_6.json";
        } else return "poe_main_7.json";
    }
}
