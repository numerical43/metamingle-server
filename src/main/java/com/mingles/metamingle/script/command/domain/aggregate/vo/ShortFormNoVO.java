package com.mingles.metamingle.script.command.domain.aggregate.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ShortFormNoVO implements Serializable {

    @Column
    private Long shortFormNo;
}
