package com.eunmok.demo.business.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestApiResponse<T> {
    private int code;
    private String message;
    private T data;



}
