package com.mingles.metamingle.member.query.application.dto.response;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberQueryResponse {

    private Long memberNo;
    private String nickname;
    private String email;
    private Role role;
    public static MemberQueryResponse from(Member member) {
        return new MemberQueryResponse(
                member.getMemberNo(),
                member.getNickname(),
                member.getEmail(),
                member.getRole()
        );
    }

}
