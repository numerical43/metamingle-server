package com.mingles.metamingle.shortform.query.application.service;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.application.controller.ShortFormQueryController;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.ShortFormInterface;
import com.mingles.metamingle.shortform.query.domain.repository.ShortFormQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShortFormQueryService {

    private final ShortFormQueryRepository shortFormQueryRepository;

    // 전체 숏폼 조회
    public List<GetShortFormListResponse> getShortFormList() {

        List<ShortForm> shortFormList = shortFormQueryRepository.findAll();

        List<GetShortFormListResponse> response = shortFormList.stream().map(
                shortForm -> {
                    //String memberNickname = memberCiRepository.findMemberNicknameByMemberNo(shortForm.getMemberNoVo().getMemberNo());

                    return new GetShortFormListResponse(
                            shortForm.getShortFormNo(),
                            shortForm.getTitle(),
                            shortForm.getThumbnailUrl(),
                            shortForm.getUrl(),
                            shortForm.getDescription(),
                            null,
                            shortForm.getDate(),
                            shortForm.getIsInteractive()
                    );
                }).collect(Collectors.toList());

        System.out.println("response = " + response.get(0).getThumbnailUrl());
        System.out.println("response = " + response.get(0).getMemberName());

        return response;
    }

    // 숏폼 하나 조회
    public GetShortFormResponse getShortForm(Long shortFormNo) {

        ShortForm shortFormResponse = shortFormQueryRepository.findShortFormByShortFormNo(shortFormNo);

        GetShortFormResponse response = new GetShortFormResponse(
                shortFormResponse.getShortFormNo(),
                shortFormResponse.getTitle(),
                shortFormResponse.getThumbnailUrl(),
                shortFormResponse.getThumbnailUrl(),
                shortFormResponse.getDescription(),
                null,
                shortFormResponse.getDate(),
                shortFormResponse.getIsInteractive());

        return response;

    }
}
