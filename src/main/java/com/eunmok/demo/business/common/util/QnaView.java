package com.eunmok.demo.business.common.util;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QnaView {
    private String subjectName;
    private List<SummaryValue> list;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SummaryValue{
        private String column;
        private int count;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SummaryMerge{
        public String subjectName;
        private int count;
        private int answerStatus;
        private LocalDate yearMonth;
        private int month;


    }
}
