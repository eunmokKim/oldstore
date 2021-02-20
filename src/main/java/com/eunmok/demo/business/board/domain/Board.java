package com.eunmok.demo.business.board.domain;

import com.eunmok.demo.business.common.domain.Category;
import com.eunmok.demo.business.common.domain.Reg;
import com.eunmok.demo.business.common.domain.Upd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ol_board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "price")
    private Integer price;
    @Embedded
    private Reg reg;
    @Embedded
    private Upd upd;

    public void created(BoardRequest req){
        this.content = req.getContent();
        this.title = req.getTitle();
        this.price = req.getPrice();
        this.reg = Reg.builder().regDt(LocalDateTime.now()).build();
    }

    public void updated(BoardRequest req){
        this.content = req.getContent();
        this.title = req.getTitle();
        this.price = req.getPrice();
        this.upd = Upd.builder().updDt(LocalDateTime.now()).build();
    }
}
