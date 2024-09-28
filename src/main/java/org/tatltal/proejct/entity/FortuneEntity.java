package org.tatltal.proejct.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "fortune")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FortuneEntity {

    @MongoId
    private ObjectId id;
    private String text;


}
