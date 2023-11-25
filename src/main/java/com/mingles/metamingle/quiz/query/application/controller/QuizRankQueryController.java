package com.mingles.metamingle.quiz.query.application.controller;

import com.mingles.metamingle.global.common.ApiResponse;
import com.mingles.metamingle.quiz.query.application.dto.response.QuizRankQueryResponse;
import com.mingles.metamingle.quiz.query.application.service.QuizRankQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizRankQueryController {

    private final QuizRankQueryService quizRankQueryService;

    @GetMapping("/quiz-rank")
    public ResponseEntity<ApiResponse> getTop12QuizList(@RequestHeader("Authentication") String token) {

        List<QuizRankQueryResponse> responseList = quizRankQueryService.getQuizTop12List();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("리스트가 성공적으로 조회되었습니다." , responseList)
        );
    }
}
