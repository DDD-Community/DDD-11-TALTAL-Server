package org.tatltal.proejct.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyRecordRequest {

    private ObjectId userId;

}
