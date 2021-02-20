package com.eunmok.demo.business.board.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BoardView {

    private Long id;
    private String title;
    private String content;

}
