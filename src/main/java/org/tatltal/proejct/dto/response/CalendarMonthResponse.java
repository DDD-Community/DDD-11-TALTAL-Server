package org.tatltal.proejct.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class CalendarMonthResponse {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CalendarMonthItemResponse{

        private Boolean entered;

        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate date;


        private Boolean goalAchieved;

    }
}
