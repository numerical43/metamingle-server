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
    private String skinColor;

    @Column
    private String hair;

    @Column
    private String hairColor;

    @Column
    private String eye;

    @Column
    private String eyeColor;

    @Column
    private String top;

    @Column
    private String topColor;

    @Column
    private String bottom;

    @Column
    private String bottomColor;

    @Column
    private String onePiece;

    @Column
    private String onPieceColor;

    @Column
    private String shoes;

    @Column
    private String ShoeColor;

    @Column
    private String gender;

    @Builder
    public Avatar(AvatarMemberNoVO avatarMemberNoVO, String skinColor, String hair, String hairColor, String eye, String eyeColor, String top, String topColor, String bottom, String bottomColor, String onePiece, String onPieceColor, String shoes, String shoeColor, String gender) {
        this.avatarMemberNoVO = avatarMemberNoVO;
        this.skinColor = skinColor;
        this.hair = hair;
        this.hairColor = hairColor;
        this.eye = eye;
        this.eyeColor = eyeColor;
        this.top = top;
        this.topColor = topColor;
        this.bottom = bottom;
        this.bottomColor = bottomColor;
        this.onePiece = onePiece;
        this.onPieceColor = onPieceColor;
        this.shoes = shoes;
        ShoeColor = shoeColor;
        this.gender = gender;
    }

}