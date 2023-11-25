package com.mingles.metamingle.movielike.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InteractiveMovieLikeVO implements Serializable {

    @Column
    private Long interactiveMovieNo;

    @Column
    private Long memberNo;

    public InteractiveMovieLikeVO(Long interactiveMovieNo, Long memberNo) {
        this.interactiveMovieNo = interactiveMovieNo;
        this.memberNo = memberNo;
    }

}
