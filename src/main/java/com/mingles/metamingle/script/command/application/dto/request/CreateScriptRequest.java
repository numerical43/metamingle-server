package com.mingles.metamingle.script.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScriptRequest {
    Long shortFormNo;
    String content;
    public CreateScriptRequest(Long shortFormNo, String content) {
        this.shortFormNo = shortFormNo;
        this.content = content;
    }
}
