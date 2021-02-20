package com.eunmok.demo.business.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reg {
    private String regId;
    private LocalDateTime regDt;
}
