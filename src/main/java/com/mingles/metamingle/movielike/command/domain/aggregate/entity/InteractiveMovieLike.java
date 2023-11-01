package com.mingles.metamingle.movielike.command.domain.aggregate.entity;

import com.mingles.metamingle.movielike.command.domain.aggregate.vo.InteractiveMovieLikeVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "TBL_INTERACTIVE_MOVIE_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InteractiveMovieLike {

    @EmbeddedId
    private InteractiveMovieLikeVO interactiveMovieLikeVO;

    @Builder
    public InteractiveMovieLike(InteractiveMovieLikeVO interactiveMovieLikeVO) {
        this.interactiveMovieLikeVO = interactiveMovieLikeVO;
    }

}
