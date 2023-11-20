package com.mingles.metamingle.quiz.command.application.dto.response;

import com.mingles.metamingle.member.command.application.dto.response.MemberCommandResponse;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.quiz.command.domain.aggregate.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizCommandResponse {

    private UUID uuid;
    public static QuizCommandResponse from(Quiz quiz) {
        return new QuizCommandResponse(
                quiz.getQuizUUID()
        );
    }

}
