package com.mingles.metamingle.shortform.query.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetShortFormResponse {

    private Long shortFormNo;
    private String title;
    private String thumbnailUrl;
    private String url;
    private String description;
    private String memberName;
    private Date date;
    private Boolean isInteractive;
    private List<InteractiveMovieDTO> interactiveMovieDTOS;

    public GetShortFormResponse(Long shortFormNo, String title, String thumbnailUrl, String url, String description,
                                    String memberName, Date date, Boolean isInteractive, List<InteractiveMovieDTO> interactiveMovieDTOS) {
        this.shortFormNo = shortFormNo;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
        this.description = description;
        this.memberName = memberName;
        this.date = date;
        this.isInteractive = isInteractive;
        this.interactiveMovieDTOS = interactiveMovieDTOS;
    }

}
