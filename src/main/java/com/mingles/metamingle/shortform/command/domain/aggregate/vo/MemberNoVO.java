package com.mingles.metamingle.shortform.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberNoVO {

    @Column
    private Long MemberNo;

}
