package com.eunmok.demo.business.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;


    public static <T> ApiResponse<T> ok(T t){
        return ApiResponse.<T>builder().code(200).message("성공").data(t).build();
    }
}
