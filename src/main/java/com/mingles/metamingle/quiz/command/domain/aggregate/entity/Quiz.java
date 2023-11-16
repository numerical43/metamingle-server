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

    @Column
    private UUID quizUUID;

    @Embedded
    private ShortFormNoVO shortFormNoVO;

    @Builder
    public Quiz(UUID quizUUID, ShortFormNoVO shortFormNoVO) {
        this.quizUUID = quizUUID;
        this.shortFormNoVO = shortFormNoVO;
    }

}
