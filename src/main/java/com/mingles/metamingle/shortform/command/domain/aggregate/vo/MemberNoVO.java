package com.mingles.metamingle.shortform.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class MemberNoVO {

    @Column
    private Long MemberNo;

}
