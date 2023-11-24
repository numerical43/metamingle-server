package com.mingles.metamingle.quiz.command.application.service;

import com.mingles.metamingle.quiz.command.application.dto.response.QuizCommandResponse;
import com.mingles.metamingle.quiz.command.domain.aggregate.entity.Quiz;
import com.mingles.metamingle.quiz.command.domain.repository.QuizCommandRepository;
import com.mingles.metamingle.quiz.query.domain.repository.QuizQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizCommandService {

    private final QuizCommandRepository quizCommandRepository;
    private final QuizQueryRepository quizQueryRepository;

    @Transactional
    public QuizCommandResponse saveQuizWithUUID(UUID uuid, String korea, String english, String isquiz) {

        Quiz quiz = quizCommandRepository.save(Quiz
                .builder()
                .quizUUID(uuid)
                .korean(korea)
                .english(english)
                .isquiz(isquiz)
                .shortFormNoVO(null)
                .build());

        return QuizCommandResponse.from(quiz);
    }


    @Transactional
    public void updateQuizWithUUID(Long shortFormNo, UUID uuid) {

        Quiz quiz = quizQueryRepository.findByQuizUUID(uuid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈 UUID 입니다"));

        quiz.update(shortFormNo);

    }

    @Transactional
    public void deleteQuizFromScheduler() {
        List<Quiz> deleteQuizList = quizCommandRepository.findByIsquizOrShortFormNoVOIsNull("no");

        quizCommandRepository.deleteAll(deleteQuizList);
    }

}
