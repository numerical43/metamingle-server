package com.mingles.metamingle.member.command.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column
    private String memberName;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String provider;

    @Column
    private String providerId;

    @Builder
    public Member(String memberName, String nickname, String email, String provider, String providerId) {
        this.memberName = memberName;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }

}