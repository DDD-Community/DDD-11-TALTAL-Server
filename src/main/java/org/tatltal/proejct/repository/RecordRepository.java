package org.tatltal.proejct.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tatltal.proejct.entity.RecordEntity;

import java.time.Instant;
import java.util.Optional;

public interface RecordRepository extends MongoRepository<RecordEntity, ObjectId> {

    Optional<RecordEntity> findByUserIdAndBaselineDate(ObjectId userId, Instant baselineDate);

}
