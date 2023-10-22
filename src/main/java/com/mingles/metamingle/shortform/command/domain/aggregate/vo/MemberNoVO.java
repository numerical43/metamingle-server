package com.mingles.metamingle.shortform.command.domain.aggregate.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class MemberNoVO {

    @Column
    private Long MemberNo;

    public MemberNoVO(Long memberNo) {
        this.MemberNo = memberNo;
    }

}
