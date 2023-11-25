package com.mingles.metamingle.member.command.application.service;

import com.mingles.metamingle.member.command.application.dto.request.RegistMemberRequest;
import com.mingles.metamingle.member.command.application.dto.request.RegistMemberTemp;
import com.mingles.metamingle.member.command.application.dto.response.MemberCommandResponse;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Role;
import com.mingles.metamingle.member.command.domain.repository.MemberCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberCommandRepository memberCommandRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberCommandResponse signup(RegistMemberTemp request) {
        if (memberCommandRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .role(Role.ROLE_MEMBER)
                .build();

        memberCommandRepository.save(member);

        return MemberCommandResponse.from(member);

    }

    public MemberCommandResponse signupAdmin(RegistMemberTemp request) {

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .role(Role.ROLE_ADMIN)
                .build();

        memberCommandRepository.save(member);

        return MemberCommandResponse.from(member);
    }


    /* Email 인증 후 회원가입
    @Transactional
    public MemberCommandResponse signup(RegistMemberRequest request) {
        if (memberCommandRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .role(Role.ROLE_MEMBER)
                .build();

        memberCommandRepository.save(member);

        return MemberCommandResponse.from(member);

    }
     */

}