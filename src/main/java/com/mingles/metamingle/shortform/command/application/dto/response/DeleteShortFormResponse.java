package com.mingles.metamingle.shortform.command.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeleteShortFormResponse {
    private Long shortFormNo;
    private List<Long> interactiveMovieNos;

    public DeleteShortFormResponse(Long shortFormNo, List<Long> interactiveMovieNos) {
        this.shortFormNo = shortFormNo;
        this.interactiveMovieNos = interactiveMovieNos;
    }
}
