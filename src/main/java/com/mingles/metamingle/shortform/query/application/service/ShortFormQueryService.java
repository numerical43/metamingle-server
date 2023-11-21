package com.mingles.metamingle.shortform.query.application.service;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.InteractiveMovieDTO;
import com.mingles.metamingle.shortform.query.application.dto.response.ShortFormLikeInfoDTO;
import com.mingles.metamingle.shortform.query.domain.repository.ShortFormQueryRepository;
import com.mingles.metamingle.shortform.query.infrastructure.service.ApiInteractiveMovieQueryService;
import com.mingles.metamingle.shortform.query.infrastructure.service.ApiMemberQueryService;
import com.mingles.metamingle.shortform.query.infrastructure.service.ApiShortFormLikeQueryService;
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
    private final ApiShortFormLikeQueryService apiShortFormLikeQueryService;

    // 전체 숏폼 조회
    public List<GetShortFormListResponse> getShortFormList(String language) {

        List<ShortForm> shortFormList = shortFormQueryRepository.findAllByOrderByShortFormNoDesc();

        List<GetShortFormListResponse> response = shortFormList.stream().map(
                shortForm -> {
                    String memberName = apiMemberQueryService.getMemberName(shortForm.getMemberNoVO().getMemberNo());

                    List<InteractiveMovieDTO> interactiveMovieDTOS;

                    if (shortForm.getIsInteractive()) {
                        interactiveMovieDTOS = apiInteractiveMovieQueryService.getRelatedInteractiveMovies(shortForm.getShortFormNo());
                    } else {
                        interactiveMovieDTOS = null;
                    }

                    ShortFormLikeInfoDTO shortFormLikeInfoDTO = apiShortFormLikeQueryService
                            .getShortFormLikeInfo(shortForm.getMemberNoVO().getMemberNo(), shortForm.getShortFormNo());

                    return new GetShortFormListResponse(
                            shortForm.getShortFormNo(),
                            shortForm.getTitle(),
                            language.equals("kr") ? shortForm.getThumbnailUrlKr() : shortForm.getThumbnailUrlEng(),
                            language.equals("kr") ? shortForm.getUrlKr() : shortForm.getUrlEng(),
                            shortForm.getDescription(),
                            memberName,
                            shortForm.getDate(),
                            shortForm.getIsInteractive(),
                            shortFormLikeInfoDTO.getIsLike(),
                            shortFormLikeInfoDTO.getShortFormLikeCnt(),
                            interactiveMovieDTOS
                    );
                }).collect(Collectors.toList());

        return response;
    }

    // 숏폼 하나 조회
    public GetShortFormResponse getShortForm(Long shortFormNo, String language) {

        ShortForm shortFormResponse = shortFormQueryRepository.findShortFormByShortFormNo(shortFormNo);

        String memberName = apiMemberQueryService.getMemberName(shortFormResponse.getMemberNoVO().getMemberNo());

        List<InteractiveMovieDTO> interactiveMovieDTOS;

        if (shortFormResponse.getIsInteractive()) {
            interactiveMovieDTOS = apiInteractiveMovieQueryService.getRelatedInteractiveMovies(shortFormNo);
        } else {
            interactiveMovieDTOS = null;
        }

        ShortFormLikeInfoDTO shortFormLikeInfoDTO = apiShortFormLikeQueryService
                .getShortFormLikeInfo(shortFormResponse.getMemberNoVO().getMemberNo(), shortFormResponse.getShortFormNo());

        GetShortFormResponse response = new GetShortFormResponse(
                shortFormResponse.getShortFormNo(),
                shortFormResponse.getTitle(),
                language.equals("kr") ? shortFormResponse.getThumbnailUrlKr() : shortFormResponse.getThumbnailUrlEng(),
                language.equals("kr") ? shortFormResponse.getUrlKr() : shortFormResponse.getUrlEng(),
                shortFormResponse.getDescription(),
                memberName,
                shortFormResponse.getDate(),
                shortFormResponse.getIsInteractive(),
                shortFormLikeInfoDTO.getIsLike(),
                shortFormLikeInfoDTO.getShortFormLikeCnt(),
                interactiveMovieDTOS
        );

        return response;

    }
}
