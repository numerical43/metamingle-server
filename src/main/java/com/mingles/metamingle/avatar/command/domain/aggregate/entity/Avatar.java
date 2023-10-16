package com.mingles.metamingle.avatar.command.domain.aggregate.entity;

import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "TBL_AVATAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Avatar implements Serializable {

    @EmbeddedId
    private AvatarMemberNoVO avatarMemberNoVO;

    @Column
    private String hair;

    @Column
    private String hairColor;

    @Column
    private String eye;

    @Column
    private String top;

    @Column
    private String bottom;

    @Column
    private String set;

    @Column
    private String shoes;

    @Column
    private String gender;

    @Builder
    public Avatar(AvatarMemberNoVO avatarMemberNoVO, String hair, String hairColor, String eye, String top, String bottom, String set, String shoes, String gender) {
        this.avatarMemberNoVO = avatarMemberNoVO;
        this.hair = hair;
        this.hairColor = hairColor;
        this.eye = eye;
        this.top = top;
        this.bottom = bottom;
        this.set = set;
        this.shoes = shoes;
        this.gender = gender;
    }


}