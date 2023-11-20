package com.mingles.metamingle.quiz.query.domain.repository;

import com.mingles.metamingle.quiz.command.domain.aggregate.entity.Quiz;
import com.mingles.metamingle.quiz.command.domain.aggregate.vo.ShortFormNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizQueryRepository extends JpaRepository<Quiz, Long> {

    Optional<Quiz> findByShortFormNoVO(ShortFormNoVO shortFormNoVO);
}
