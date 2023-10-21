package com.mingles.metamingle.interativemovie.command.application.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateInteractiveMovieResponse {

    Long shortFormNo;
    Long interactiveMovieNo;
    String thumbnailUrl;
    String url;
    int order;

}