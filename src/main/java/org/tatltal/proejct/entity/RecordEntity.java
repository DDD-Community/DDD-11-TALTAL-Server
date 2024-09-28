package org.tatltal.proejct.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "record")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecordEntity {

    @MongoId
    private ObjectId id;

    private ObjectId userId;

    private Instant baselineDate;

    private int recordCount;

    public RecordEntity(ObjectId userId, Instant baselineDate, int recordCount) {
        this.userId = userId;
        this.recordCount = recordCount;
        this.baselineDate = baselineDate;
    }
}
