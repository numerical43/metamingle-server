package com.mingles.metamingle.member.query.application.dto.response;


import com.mingles.metamingle.member.command.domain.aggregate.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String nickname;

}
