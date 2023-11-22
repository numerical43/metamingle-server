package com.mingles.metamingle.shortform.command.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShortFormResponse {

    private Long shortFormNo;
    private String thumbnailUrlKr;
    private String urlKr;
    private String thumbnailUrlEng;
    private String urlEng;

    public CreateShortFormResponse(Long shortFormNo, String thumbnailUrlKr, String urlKr, String thumbnailUrlEng, String urlEng) {
        this.shortFormNo = shortFormNo;
        this.thumbnailUrlKr = thumbnailUrlKr;
        this.urlKr = urlKr;
        this.thumbnailUrlEng = thumbnailUrlEng;
        this.urlEng = urlEng;
    }

}
