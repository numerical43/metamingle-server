package com.mingles.metamingle.interativemovie.query.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetInteractiveMovieResponse {

    Long interactiveMovieNo;
    String title;
    String thumbnailUrl;
    String url;
    String description;
    String memberName;
    Date date;
    int sequence;

    public GetInteractiveMovieResponse(Long interactiveMovieNo, String title, String thumbnailUrl, String url, String description,
                                String memberName, Date date, int sequence) {
        this.interactiveMovieNo = interactiveMovieNo;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
        this.description = description;
        this.memberName = memberName;
        this.date = date;
        this.sequence = sequence;
    }
}
