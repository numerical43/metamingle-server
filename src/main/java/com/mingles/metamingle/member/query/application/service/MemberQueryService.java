package com.mingles.metamingle.member.query.application.service;

import com.mingles.metamingle.global.auth.JwtTokenProvider;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.query.application.dto.request.FindMemberRequest;
import com.mingles.metamingle.member.query.application.dto.request.LoginRequest;
import com.mingles.metamingle.member.query.application.dto.response.LoginResponse;
import com.mingles.metamingle.member.query.application.dto.response.MemberQueryResponse;
import com.mingles.metamingle.member.query.infrastructure.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberQueryResponse findMemberByNo(FindMemberRequest request) {

        Member member = memberQueryRepository.findById(request.getMemberNo())
                .orElseThrow(() -> new NotFoundException("해당 회원이 존재하지 않습니다."));

        return MemberQueryResponse.from(member);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        Member member = memberQueryRepository.findMemberByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            System.out.println("비밀번호 일치 안함");
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                member, null, member.getAuthorities());

        LoginResponse response = new LoginResponse(jwtTokenProvider.generateAccessToken(member), member.getNickname());

        return response;
    }

    @Transactional(readOnly = true)
    public Long getMemberInfo(String token) {

        Long result = jwtTokenProvider.getMemberNoFromToken(token);

        return result;
    }

}