package com.mingles.metamingle.shortform.command.application.dto.response;

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
