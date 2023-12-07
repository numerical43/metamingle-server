package com.mingles.metamingle.translate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Translation {
    private String detected_source_language;
    private String text;
}
