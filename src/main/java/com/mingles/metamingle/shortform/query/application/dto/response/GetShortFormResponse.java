package com.mingles.metamingle.shortform.query.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetShortFormResponse {

    Long shortFormNo;
    String title;
    String thumbnailUrl;
    String url;
    String description;
    String memberName;
    Date date;
    Boolean isInteractive;

    public GetShortFormResponse(Long shortFormNo, String title, String thumbnailUrl, String url, String description,
                                    String memberName, Date date, Boolean isInteractive) {
        this.shortFormNo = shortFormNo;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
        this.description = description;
        this.memberName = memberName;
        this.date = date;
        this.isInteractive = isInteractive;
    }

}
