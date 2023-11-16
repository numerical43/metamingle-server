package com.mingles.metamingle.avatar.query.application.service;

import com.mingles.metamingle.avatar.command.domain.aggregate.entity.Avatar;
import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import com.mingles.metamingle.avatar.query.application.dto.response.AvatarQueryResponse;
import com.mingles.metamingle.avatar.query.infrastructure.repository.AvatarQueryRepository;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.query.application.dto.request.FindMemberRequest;
import com.mingles.metamingle.member.query.application.dto.response.MemberQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvatarQueryService {

    private final AvatarQueryRepository avatarQueryRepository;

    @Transactional(readOnly = true)
    public AvatarQueryResponse findAvatarByMemberNo(Long memberNo) {

        Avatar avatar = avatarQueryRepository.findByAvatarMemberNoVO(new AvatarMemberNoVO(memberNo))
                .orElseThrow(() -> new IllegalArgumentException("해당 회원의 아바타가 존재하지 않습니다."));

        System.out.println("avatar.getAvatarData(). = " + new String(avatar.getAvatarData()));

        return AvatarQueryResponse.from(avatar);
    }
}