package com.eunmok.demo.business.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeignAnswerResponse {
    public String subjectName;
    private int count;
    private int answerStatus;
}
