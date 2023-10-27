package com.mingles.metamingle.shortform.query.application.service;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.InteractiveMovieDTO;
import com.mingles.metamingle.shortform.query.domain.repository.ShortFormQueryRepository;
import com.mingles.metamingle.shortform.query.infrastructure.service.ApiInteractiveMovieQueryService;
import com.mingles.metamingle.shortform.query.infrastructure.service.ApiMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShortFormQueryService {

    private final ShortFormQueryRepository shortFormQueryRepository;
    private final ApiMemberQueryService apiMemberQueryService;
    private final ApiInteractiveMovieQueryService apiInteractiveMovieQueryService;

    // 전체 숏폼 조회
    public List<GetShortFormListResponse> getShortFormList() {

        List<ShortForm> shortFormList = shortFormQueryRepository.findAllByOrderByShortFormNoDesc();

        List<GetShortFormListResponse> response = shortFormList.stream().map(
                shortForm -> {
                    String memberName = apiMemberQueryService.getMemberName(shortForm.getMemberNoVO().getMemberNo());

                    return new GetShortFormListResponse(
                            shortForm.getShortFormNo(),
                            shortForm.getTitle(),
                            shortForm.getThumbnailUrl(),
                            shortForm.getUrl(),
                            shortForm.getDescription(),
                            memberName,
                            shortForm.getDate(),
                            shortForm.getIsInteractive()
                    );
                }).collect(Collectors.toList());

        return response;
    }

    // 숏폼 하나 조회
    public GetShortFormResponse getShortForm(Long shortFormNo) {

        ShortForm shortFormResponse = shortFormQueryRepository.findShortFormByShortFormNo(shortFormNo);

        String memberName = apiMemberQueryService.getMemberName(shortFormResponse.getMemberNoVO().getMemberNo());

        List<InteractiveMovieDTO> interactiveMovieDTOS;

        if (shortFormResponse.getIsInteractive()) {
            interactiveMovieDTOS = apiInteractiveMovieQueryService.getRelatedInteractiveMovies(shortFormNo);
        } else {
            interactiveMovieDTOS = null;
        }

        GetShortFormResponse response = new GetShortFormResponse(
                shortFormResponse.getShortFormNo(),
                shortFormResponse.getTitle(),
                shortFormResponse.getThumbnailUrl(),
                shortFormResponse.getThumbnailUrl(),
                shortFormResponse.getDescription(),
                memberName,
                shortFormResponse.getDate(),
                shortFormResponse.getIsInteractive(),
                interactiveMovieDTOS
        );

        return response;

    }
}
