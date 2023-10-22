package com.mingles.metamingle.shortform.query.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InteractiveMovieDTO {
    private String url;
    private String choice;
    private Long interactiveMovieNo;

    public InteractiveMovieDTO(String url, String choice, Long interactiveMovieNo) {
        this.url = url;
        this.choice = choice;
        this.interactiveMovieNo = interactiveMovieNo;
    }
}
