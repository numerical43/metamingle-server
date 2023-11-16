package com.mingles.metamingle.avatar.command.domain.aggregate.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class AvatarMemberNoVO {

    @Column
    private Long memberNo;
    public AvatarMemberNoVO(long memberNo){
        this.memberNo = memberNo;
    }

}