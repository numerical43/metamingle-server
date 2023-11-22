package com.mingles.metamingle.interactivemovie.command.application.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class SubtitledVideo {
    MultipartFile fileKr;
    MultipartFile fileEng;

    public SubtitledVideo(MultipartFile fileKr, MultipartFile fileEng) {
        this.fileKr = fileKr;
        this.fileEng = fileEng;
    }
}