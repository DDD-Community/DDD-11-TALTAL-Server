package org.tatltal.proejct.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tatltal.proejct.entity.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    boolean existsByNickname(String nickname);

    List<UserEntity> findByNotificationTrue();
}
