package com.mingles.metamingle.interativemovie.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VideoMetadata {
    private String title;
    private String description;
    private List<String> choices;
}
