package com.mingles.metamingle.member.command.application.dto.request;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistMemberRequest {

    private String email;
    private String nickname;
    private String password;

}
