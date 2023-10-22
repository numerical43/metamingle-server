package com.mingles.metamingle.member.query.application.service;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.query.application.dto.request.FindMemberRequest;
import com.mingles.metamingle.member.query.application.dto.response.MemberQueryResponse;
import com.mingles.metamingle.member.query.infrastructure.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    @Transactional(readOnly = true)
    public MemberQueryResponse findMemberByNo(FindMemberRequest request) {

        Member member = memberQueryRepository.findById(request.getMemberNo())
                .orElseThrow(() -> new NotFoundException("해당 회원이 존재하지 않습니다."));

        return MemberQueryResponse.from(member);
    }
}