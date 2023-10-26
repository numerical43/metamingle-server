package com.mingles.metamingle.shortform.query.infrastructure.service;

import com.mingles.metamingle.interactivemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interactivemovie.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.interactivemovie.query.domain.repository.InteractiveMovieQueryRepository;
import com.mingles.metamingle.shortform.query.application.dto.response.InteractiveMovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiInteractiveMovieService {

    private final InteractiveMovieQueryRepository interactiveMovieQueryRepository;

    public List<InteractiveMovieDTO> getRelatedInteractiveMovies(Long shortFormNo) {

        ShortFormNoVO shortFormNoVO = new ShortFormNoVO(shortFormNo);

        List<InteractiveMovie> interactiveMovies = interactiveMovieQueryRepository.findInteractiveMoviesByShortFormNoVO(shortFormNoVO);

        List<InteractiveMovieDTO> interactiveMovieDTOS = new ArrayList<>();

        for (InteractiveMovie interactiveMovie : interactiveMovies) {

            InteractiveMovieDTO dto = new InteractiveMovieDTO(
                    interactiveMovie.getUrl(),
                    interactiveMovie.getChoice(),
                    interactiveMovie.getInteractiveMovieNo()
            );

            interactiveMovieDTOS.add(dto);

        }

        return interactiveMovieDTOS;
    }

}
