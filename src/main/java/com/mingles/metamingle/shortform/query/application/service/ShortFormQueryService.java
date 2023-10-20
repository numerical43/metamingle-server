package com.mingles.metamingle.shortform.query.application.service;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.application.controller.ShortFormQueryController;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import com.mingles.metamingle.shortform.query.domain.repository.ShortFormQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortFormQueryService {

    private final ShortFormQueryRepository shortFormQueryRepository;

    // 전체 숏폼 조회
    public List<GetShortFormListResponse> getShortFormList() {

        List<ShortForm> shortFormList = shortFormQueryRepository.findAllShortFormList();

        List<GetShortFormListResponse> response = new ArrayList<>();

    }

    // 숏폼 하나 조회
    public GetShortFormResponse getShortForm(Long shortFormNo) {

        GetShortFormResponse response = shortFormQueryRepository.findShortFormByShortFormNo(shortFormNo);

        return response;

    }
}
