package com.mingles.metamingle.avatar.command.domain.aggregate.entity;

import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "TBL_AVATAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Avatar implements Serializable {

    @EmbeddedId
    private AvatarMemberNoVO avatarMemberNoVO;

    @Size(max = 3000)
    private String avatarData;

    @Builder
    public Avatar(AvatarMemberNoVO avatarMemberNoVO, String avatarData) {
        this.avatarMemberNoVO = avatarMemberNoVO;
        this.avatarData = avatarData;
    }

}