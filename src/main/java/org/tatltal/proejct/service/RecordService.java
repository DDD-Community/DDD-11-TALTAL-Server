package org.tatltal.proejct.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.tatltal.proejct.entity.RecordEntity;
import org.tatltal.proejct.repository.RecordRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    public static final ZoneId SEOUL = ZoneId.of("Asia/Seoul");
    private final RecordRepository recordRepository;
    private final MongoTemplate mongoTemplate;


    public void newRecord(ObjectId userId) {
        Instant now = Instant.now().truncatedTo(ChronoUnit.MINUTES);
        RecordEntity recordEntity = recordRepository.findByUserIdAndBaselineDate(userId, now).orElse(new RecordEntity(userId, now, 0));
        recordEntity.setRecordCount(recordEntity.getRecordCount() + 1);
        recordRepository.save(recordEntity);
    }

    public Integer getTodayRecord(ObjectId userId) {
        Instant today = ZonedDateTime.now(SEOUL).truncatedTo(ChronoUnit.DAYS).toInstant();
        Instant tomorrow = today.plus(1L, ChronoUnit.DAYS);

        List<RecordEntity> todayRecordList = getTodayRecordList(userId, today, tomorrow);


        return todayRecordList.stream().mapToInt(RecordEntity::getRecordCount).sum();
    }

    public List<RecordEntity> getTodayRecordList(ObjectId userId, Instant today, Instant tomorrow) {
        Query query = new Query();
        query.addCriteria(
                new Criteria("userId").is(userId)
                        .and("baselineDate").gte(today)
                        .lt(tomorrow)
        );

        return mongoTemplate.find(query, RecordEntity.class);
    }

    public Map<LocalDate, Integer> getMonthRecord(ObjectId userId) {

        ZonedDateTime now = ZonedDateTime.now(SEOUL);
        ZonedDateTime twoYearsAgo = now.minusYears(2L);
        ZonedDateTime tomorrow = now.plusDays(1L);


        Query query = new Query();
        Criteria criteria = new Criteria("userId").is(userId)
                .and("baselineDate").gte(twoYearsAgo.toInstant())
                .lt(tomorrow.toInstant());

        query.addCriteria(criteria);

        List<RecordEntity> recordEntityList = mongoTemplate.find(query, RecordEntity.class);

        return recordEntityList.stream()
                .collect(
                        Collectors.groupingBy(
                                recordEntity -> recordEntity
                                        .getBaselineDate()
                                        .atZone(SEOUL)
                                        .toLocalDate(),
                                Collectors.summingInt(RecordEntity::getRecordCount))
                );

    }
}
