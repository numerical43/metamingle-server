package com.mingles.metamingle.avatar.command.domain.aggregate.entity;

import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import lombok.AccessLevel;
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

    //enum 생각해보기티비

}