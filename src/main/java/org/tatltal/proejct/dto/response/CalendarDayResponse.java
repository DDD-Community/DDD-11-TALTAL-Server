package org.tatltal.proejct.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CalendarDayResponse {

    int shot;


    private List<CalendarDayItemResponse> poisonRecord;
}
