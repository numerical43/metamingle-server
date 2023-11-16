package com.mingles.metamingle.avatar.command.application.dto.response;

import com.mingles.metamingle.avatar.command.domain.aggregate.entity.Avatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvatarCommandResponse {

    private Long memberNo;

    public static AvatarCommandResponse from(Avatar avatar) {
        return new AvatarCommandResponse(
                avatar.getAvatarMemberNoVO().getMemberNo()
        );
    }
}
