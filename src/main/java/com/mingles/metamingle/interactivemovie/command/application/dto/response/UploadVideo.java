package com.mingles.metamingle.interactivemovie.command.application.dto.response;

import lombok.*;

@Getter
@Setter
public class UploadVideo {
    private String url;
    private String thumbnailUrl;

    public UploadVideo(String url, String thumbnailUrl) {
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}
