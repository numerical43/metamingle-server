package com.mingles.metamingle.quiz.query.application.service;

import com.mingles.metamingle.quiz.command.domain.aggregate.entity.QuizRank;
import com.mingles.metamingle.quiz.query.application.dto.response.QuizRankQueryResponse;
import com.mingles.metamingle.quiz.query.domain.repository.RankQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizRankQueryService {

    private final RankQueryRepository rankQueryRepository;

    @Transactional(readOnly = true)
    public List<QuizRankQueryResponse> getQuizTop12List() {

        List<QuizRank> rankList = rankQueryRepository.findAll();
        List<QuizRankQueryResponse> responseList = new ArrayList<>();

        for (QuizRank quizRank : rankList) {
            QuizRankQueryResponse response = new QuizRankQueryResponse();
            response.setRankNo(quizRank.getRankNo());
            response.setEnglish(quizRank.getEnglish());
            response.setKorean(quizRank.getKorean());
            response.setShortFormNo(quizRank.getShortFormNo().getShortFormNo());
            responseList.add(response);
        }

        return responseList;
    }

}
