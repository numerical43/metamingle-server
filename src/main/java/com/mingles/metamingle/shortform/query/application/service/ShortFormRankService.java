package com.mingles.metamingle.shortform.query.application.service;

import com.mingles.metamingle.quiz.command.domain.aggregate.entity.Quiz;
import com.mingles.metamingle.quiz.command.domain.aggregate.entity.QuizRank;
import com.mingles.metamingle.quiz.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.quiz.command.domain.repository.RankCommandRepository;
import com.mingles.metamingle.quiz.query.domain.repository.QuizQueryRepository;
import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.domain.repository.ShortFormQueryRepository;
import com.mingles.metamingle.shortformlike.query.domain.repository.ShortFormLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShortFormRankService {

    private final ShortFormQueryRepository shortFormQueryRepository;
    private final ShortFormLikeQueryRepository shortFormLikeQueryRepository;
    private final RankCommandRepository rankCommandRepository;
    private final QuizQueryRepository quizQueryRepository;

    @Transactional
    public void getTop12LikedQuiz() {

        List<Object[]> topLikedShortFormNos = shortFormLikeQueryRepository.findTop12LikedShortForms();

        List<Long> shortFormNos = topLikedShortFormNos.stream()
                .map(array -> (Long) array[0])
                .collect(Collectors.toList());

        List<ShortForm> top12LikedQuizList = shortFormQueryRepository.findByShortFormNoIn(shortFormNos);

        for (int i = 0; i < 12; i++) {
            ShortForm shortForm = top12LikedQuizList.get(i);

            Optional<Quiz> optionalQuiz = quizQueryRepository.findByShortFormNoVO(new ShortFormNoVO(shortForm.getShortFormNo()));

            if(optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();

                QuizRank quizRank = QuizRank.builder()
                        .rankNo(i + 1)
                        .date(LocalDate.now().minusDays(1))
                        .english(quiz.getEnglish())
                        .korean(quiz.getKorean())
                        .shortFormNo(new ShortFormNoVO(shortForm.getShortFormNo()))
                        .build();
                rankCommandRepository.save(quizRank);
            }

        }
    }
}
