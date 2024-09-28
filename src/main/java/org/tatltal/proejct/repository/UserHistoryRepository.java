package org.tatltal.proejct.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tatltal.proejct.entity.UserHistoryEntity;

import java.util.Optional;

public interface UserHistoryRepository extends MongoRepository<UserHistoryEntity, ObjectId> {
    Optional<UserHistoryEntity> findByUserId(ObjectId userId);
}
