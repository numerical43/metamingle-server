package com.mingles.metamingle.interativemovie.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNoVO {

    @Column
    private Long memberNo;

    public MemberNoVO(Long memberNo) {
        this.memberNo = memberNo;
    }

}
