package com.mingles.metamingle.interactivemovie.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VideoData {
    private List<String> files;
    private VideoMetadata videoMetadata;
}
