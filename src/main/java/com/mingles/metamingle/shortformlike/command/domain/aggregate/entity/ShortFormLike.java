package com.mingles.metamingle.shortformlike.command.domain.aggregate.entity;

import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "TBL_SHORT_FORM_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortFormLike {

    @EmbeddedId
    private ShortFormLikeVO shortFormLikeVO;


    @Builder
    public ShortFormLike(ShortFormLikeVO shortFormLikeVO) {
        this.shortFormLikeVO = shortFormLikeVO;
    }
}
