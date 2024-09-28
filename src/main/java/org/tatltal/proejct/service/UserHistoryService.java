package org.tatltal.proejct.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.tatltal.proejct.entity.UserHistoryEntity;
import org.tatltal.proejct.repository.UserHistoryRepository;

import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserHistoryService {
    private final UserHistoryRepository userHistoryRepository;

    public void updateFirstTime(ObjectId userId) {
        UserHistoryEntity userHistoryEntity = userHistoryRepository.findByUserId(userId).orElse(new UserHistoryEntity(
                null,
                userId,
                new HashMap<>()
        ));
        Map<String, Boolean> history = userHistoryEntity.getHistory();
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
        history.put(now.toString(), true);
        userHistoryRepository.save(userHistoryEntity);

    }

    public UserHistoryEntity getByUserId(ObjectId userId) {
        return userHistoryRepository.findByUserId(userId).orElse(new UserHistoryEntity());

    }
}
