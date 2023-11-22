package com.mingles.metamingle.quiz.query.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRankQueryResponse {

    private int rankNo;
    private String english;
    private String korean;
    private Long shortFormNo;

}
