package com.mingles.metamingle.member.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailAuthResponse {

    private String code;

    public static MailAuthResponse from(String verifiedCode) {
        return new MailAuthResponse(verifiedCode);
    }
}
