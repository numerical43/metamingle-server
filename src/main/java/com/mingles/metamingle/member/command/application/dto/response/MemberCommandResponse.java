package com.mingles.metamingle.member.command.application.dto.response;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCommandResponse {

    private String email;

    public static MemberCommandResponse from(Member member) {
        return new MemberCommandResponse(
                member.getEmail()
        );
    }
}
