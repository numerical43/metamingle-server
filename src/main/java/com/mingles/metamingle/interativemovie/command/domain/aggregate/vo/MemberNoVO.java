package com.mingles.metamingle.interativemovie.command.domain.aggregate.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class MemberNoVO {

    @Column
    private Long memberNo;

    public MemberNoVO(Long memberNo) {
        this.memberNo = memberNo;
    }

}
