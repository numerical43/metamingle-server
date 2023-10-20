package com.mingles.metamingle.script.command.domain.aggregate.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ScriptMemberNoVO {

    @Column
    private Long memberNo;

}