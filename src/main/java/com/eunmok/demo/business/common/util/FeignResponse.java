package com.eunmok.demo.business.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeignResponse {
    public String subjectName;
    private int count;
    private String yearMonth;
    private int month;

    public LocalDate getYearMonth(){
        return LocalDate.of(Integer.parseInt(yearMonth.substring(0, 4)), Integer.parseInt(yearMonth.substring(4, 6)), 1);
    }
}
