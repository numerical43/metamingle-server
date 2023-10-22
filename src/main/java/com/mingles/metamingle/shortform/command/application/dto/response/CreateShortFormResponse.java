package com.mingles.metamingle.shortform.command.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShortFormResponse {

    Long shortFormNo;
    String thumbnailUrl;
    String url;

    public CreateShortFormResponse(Long shortFormNo, String thumbnailUrl, String url) {
        this.shortFormNo = shortFormNo;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
    }

}
