package com.mingles.metamingle.movielike.command.application.service;

import com.mingles.metamingle.movielike.command.application.dto.response.CreateInteractiveMovieLikeResponse;
import com.mingles.metamingle.movielike.command.domain.aggregate.entity.InteractiveMovieLike;
import com.mingles.metamingle.movielike.command.domain.aggregate.vo.InteractiveMovieLikeVO;
import com.mingles.metamingle.movielike.command.domain.repository.InteractiveMovieLikeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InteractiveMovieLikeCommandService {

    private final InteractiveMovieLikeCommandRepository interactiveMovieLikeCommandRepository;


    @Transactional
    public CreateInteractiveMovieLikeResponse createOrUpdateInteractiveMovieLike(Long memberNo, Long shortFormNo) {

        InteractiveMovieLikeVO interactiveMovieLikeVO = new InteractiveMovieLikeVO(memberNo, shortFormNo);

        Optional<InteractiveMovieLike> optionalInteractiveMovieLike = interactiveMovieLikeCommandRepository.findInteractiveMovieLikeByInteractiveMovieLikeVO(interactiveMovieLikeVO);


        if (optionalInteractiveMovieLike.isEmpty()) {

            InteractiveMovieLike interactiveMovieLike = new InteractiveMovieLike(interactiveMovieLikeVO);
            interactiveMovieLikeCommandRepository.save(interactiveMovieLike);

            return new CreateInteractiveMovieLikeResponse(true);

        }

        interactiveMovieLikeCommandRepository.delete(optionalInteractiveMovieLike.get());

        return new CreateInteractiveMovieLikeResponse(false);
    }

}
