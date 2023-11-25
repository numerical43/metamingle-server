package com.mingles.metamingle.shortform.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizUUIDVO {

    @Column
    UUID quizUUID;
}
