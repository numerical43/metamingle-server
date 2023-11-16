package com.mingles.metamingle.quiz.command.domain.aggregate.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class ShortFormNoVO {

    @Column
    private Long shortFormNo;

    public ShortFormNoVO(Long shortFormNo) {
        this.shortFormNo = shortFormNo;
    }

}
