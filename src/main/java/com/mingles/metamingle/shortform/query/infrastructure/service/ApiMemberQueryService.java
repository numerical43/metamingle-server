package com.mingles.metamingle.shortform.query.infrastructure.service;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.query.infrastructure.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiMemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    public String getMemberName(Long memberNo) {

        Optional<Member> optionalMember = memberQueryRepository.findById(memberNo);

        String memberName = optionalMember.get().getCreator();

        return memberName;

    }

}
