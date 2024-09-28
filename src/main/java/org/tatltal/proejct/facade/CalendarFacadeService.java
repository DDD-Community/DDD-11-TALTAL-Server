package org.tatltal.proejct.facade;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.tatltal.proejct.dto.domain.UserDomain;
import org.tatltal.proejct.dto.response.CalendarDayItemResponse;
import org.tatltal.proejct.dto.response.CalendarDayResponse;
import org.tatltal.proejct.dto.type.PoisonState;
import org.tatltal.proejct.entity.RecordEntity;
import org.tatltal.proejct.entity.UserHistoryEntity;
import org.tatltal.proejct.service.RecordService;
import org.tatltal.proejct.service.UserHistoryService;
import org.tatltal.proejct.service.UserService;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.tatltal.proejct.service.RecordService.SEOUL;

@Service
@RequiredArgsConstructor

public class CalendarFacadeService {
    private final UserHistoryService userHistoryService;
    private final RecordService recordService;
    private final UserService userService;

    public Map<LocalDate, PoisonState> getMonthRecord(ObjectId userId) {

        UserDomain user = userService.getUser(userId);
        int targetNum = user.getTargetNum();
        UserHistoryEntity userHistoryEntity = userHistoryService.getByUserId(userId);
        Map<LocalDate, Integer> monthRecord = recordService.getMonthRecord(userId);

        Map<String, Boolean> history = Optional.ofNullable(userHistoryEntity.getHistory()).orElse(new HashMap<>());


        List<LocalDate> datesInMonth = getDatesInMonth();
        Map<LocalDate, PoisonState> collect = datesInMonth.stream()
                .collect(Collectors.toMap(
                        localDate -> localDate,
                        localDate -> {
                            Boolean value = history.get(localDate.toString());
                            if (value != null && value && monthRecord.getOrDefault(localDate, 0) <= targetNum) {
                                return PoisonState.SUCCESS;
                            } else if (value != null && value && monthRecord.getOrDefault(localDate, 0) > targetNum) {
                                return PoisonState.FAIL;
                            } else {
                                return PoisonState.NONE;
                            }
                        }
                ));
        LocalDate today = LocalDate.now(SEOUL);
        collect.put(today, PoisonState.NONE);

        return collect;
    }

    public CalendarDayResponse getDayRecord(ObjectId userId, int year, int month, int day) {

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(year, month, day, 0, 0, 0), ZoneId.of("Asia/Seoul"));
        ZonedDateTime plus = zonedDateTime.plusDays(1L);
        List<RecordEntity> todayRecordList = recordService.getTodayRecordList(userId, zonedDateTime.toInstant(), plus.toInstant());

        List<CalendarDayItemResponse> list = todayRecordList.stream().map(
                todayRecord -> new CalendarDayItemResponse(todayRecord.getBaselineDate().atZone(ZoneId.of("Asia/Seoul")), todayRecord.getRecordCount())
        ).toList();

        int totalCount = list.stream().mapToInt(CalendarDayItemResponse::getShot).sum();
        return new CalendarDayResponse(totalCount, list);
    }

    private List<LocalDate> getDatesInMonth() {
        ZonedDateTime now = ZonedDateTime.now(SEOUL);
        ZonedDateTime zonedDateTime = now.minusYears(2L);
        LocalDate today = now.toLocalDate();

        List<LocalDate> localDateList = new ArrayList<>();
        LocalDate localDate = zonedDateTime.toLocalDate();
        while (!localDate.isAfter(today)) {

            localDateList.add(localDate);
            localDate = localDate.plusDays(1L);
        }

        return localDateList;
    }
}
