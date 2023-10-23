package com.mingles.metamingle.script.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateScriptRequest {
    private Long shortFormNo;
    private String content;
    public CreateScriptRequest(Long shortFormNo, String content) {
        this.shortFormNo = shortFormNo;
        this.content = content;
    }
}
