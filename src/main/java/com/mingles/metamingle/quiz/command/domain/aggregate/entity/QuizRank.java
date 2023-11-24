package com.mingles.metamingle.quiz.command.domain.aggregate.entity;

import com.mingles.metamingle.quiz.command.domain.aggregate.vo.ShortFormNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Table(name = "TBL_QUIZ_RANK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizRank {

    @Id
    private Integer rankNo;

    @Column
    private LocalDate date;

    @Column
    private String korean;

    @Column
    private String english;

    @Column
    private ShortFormNoVO shortFormNo;

    @Builder
    public QuizRank(int rankNo, LocalDate date, String korean, String english, ShortFormNoVO shortFormNo) {
        this.rankNo = rankNo;
        this.date = date;
        this.korean = korean;
        this.english = english;
        this.shortFormNo = shortFormNo;
    }
}
