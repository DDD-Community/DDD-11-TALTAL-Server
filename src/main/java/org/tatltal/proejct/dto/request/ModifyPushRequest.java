package org.tatltal.proejct.dto.request;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@NoArgsConstructor
@Setter
public class ModifyPushRequest {

    private boolean push;
    private ObjectId userId;
}
