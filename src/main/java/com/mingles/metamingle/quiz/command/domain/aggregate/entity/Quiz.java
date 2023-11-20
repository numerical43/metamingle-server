package com.mingles.metamingle.quiz.command.domain.aggregate.entity;

import com.mingles.metamingle.quiz.command.domain.aggregate.vo.ShortFormNoVO;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "TBL_QUIZ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizNo;

    @Column(columnDefinition = "BINARY(16)")
    private UUID quizUUID;

    @Column
    private String korean;

    @Column
    private String english;

    @Column
    private String isquiz;

    @Embedded
    private ShortFormNoVO shortFormNoVO;

    @Builder
    public Quiz(UUID quizUUID, String korean, String english, String isquiz, ShortFormNoVO shortFormNoVO) {
        this.quizUUID = quizUUID;
        this.korean = korean;
        this.english = english;
        this.isquiz = isquiz;
        this.shortFormNoVO = shortFormNoVO;
    }

}
