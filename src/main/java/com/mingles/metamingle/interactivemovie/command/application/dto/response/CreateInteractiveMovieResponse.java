package com.mingles.metamingle.interactivemovie.command.application.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateInteractiveMovieResponse {

    private Long shortFormNo;
    private Long interactiveMovieNo;
    private String thumbnailUrl;
    private String url;
    private String choice;
    private int sequence;

}