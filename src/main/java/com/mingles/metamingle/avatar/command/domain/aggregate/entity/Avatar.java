package com.mingles.metamingle.avatar.command.domain.aggregate.entity;

import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "TBL_AVATAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avatarNo;

    @Embedded
    private AvatarMemberNoVO avatarMemberNoVO;

    @Column(length = 3000)
    private String avatarData;

//    @Lob
//    @Column(columnDefinition = "BLOB")
//    private byte[] avatarData;

    @Builder
    public Avatar(AvatarMemberNoVO avatarMemberNoVO, String avatarData) {
        this.avatarMemberNoVO = avatarMemberNoVO;
        this.avatarData = avatarData;
    }

    public void update(String avatarData) {
        this.avatarData = avatarData;
    }

}