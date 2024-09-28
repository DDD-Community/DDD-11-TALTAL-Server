package org.tatltal.proejct.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.Map;

@Document("user_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserHistoryEntity {

    @MongoId
    private ObjectId id;

    private ObjectId userId;

    private Map<String,Boolean> history;

}
