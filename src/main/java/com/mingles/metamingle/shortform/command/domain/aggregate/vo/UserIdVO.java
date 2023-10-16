package com.mingles.metamingle.shortform.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserIdVO {

    @Column
    private Long userId;

}
