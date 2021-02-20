package com.eunmok.demo.business.board.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class BoardRequest {
    private Long id;
    private String title;
    private String content;
    private String name;
    private Integer price;
}
