package com.mingles.metamingle.quiz.command.domain.service;

import com.mingles.metamingle.quiz.command.application.service.QuizCommandService;
import com.mingles.metamingle.shortform.query.application.service.ShortFormRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@RequiredArgsConstructor
public class RankScheduler {

    private final QuizCommandService quizCommandService;
    private final ShortFormRankService shortFormRankService;

    @Scheduled(cron = "0 0 3 * * *")
    public void insertData() {
        //isQuiz = no / shortFormNo = null 인 퀴즈 테이블 drop
        quizCommandService.deleteQuizFromScheduler();

        //퀴즈 데이터 좋아요 정렬 후 삽입
        shortFormRankService.getTop12LikedQuiz();
    }
}
