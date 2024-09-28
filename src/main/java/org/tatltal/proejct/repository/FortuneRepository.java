package org.tatltal.proejct.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tatltal.proejct.entity.FortuneEntity;

public interface FortuneRepository extends MongoRepository<FortuneEntity, ObjectId> {
}
