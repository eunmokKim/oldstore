package com.eunmok.demo.business.common.domain;

import com.eunmok.demo.business.common.domain.Reg;
import com.eunmok.demo.business.common.domain.Upd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ol_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "idx")
    private Integer idx;
    @Embedded
    private Reg reg;
    @Embedded
    private Upd upd;
}
