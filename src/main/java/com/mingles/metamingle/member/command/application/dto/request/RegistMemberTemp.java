package com.mingles.metamingle.member.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistMemberTemp {

    private String email;
    private String nickname;
    private String password;
}
