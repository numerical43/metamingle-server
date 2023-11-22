package com.mingles.metamingle.interactivemovie.command.application.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateInteractiveMovieResponse {

    private Long shortFormNo;
    private Long interactiveMovieNo;
    private String thumbnailUrlKr;
    private String urlKr;
    private String thumbnailUrlEng;
    private String urlEng;
    private String choice;
    private int sequence;

}