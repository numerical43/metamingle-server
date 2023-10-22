package com.mingles.metamingle.member.query.application.dto.response;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class MemberQueryResponse {

    private Long memberNo;
    private String memberName;
    private String nickname;
    private String email;
    private String provider;
    private String providerId;

    public static MemberQueryResponse from(Member member) {
        return new MemberQueryResponse(
                member.getMemberNo(),
                member.getMemberName(),
                member.getNickname(),
                member.getEmail(),
                member.getProvider(),
                member.getProviderId()
        );
    }

}
