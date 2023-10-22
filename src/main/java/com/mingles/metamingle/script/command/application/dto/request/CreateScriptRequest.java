package com.mingles.metamingle.script.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScriptRequest {
    Long memberNo;
    Long shortFormNo;
    String content;
    public CreateScriptRequest(Long memberNo, Long shortFormNo, String content) {
        this.memberNo = memberNo;
        this.shortFormNo = shortFormNo;
        this.content = content;
    }
}
