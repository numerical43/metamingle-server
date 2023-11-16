package com.mingles.metamingle.avatar.query.application.dto.response;

import com.mingles.metamingle.avatar.command.domain.aggregate.entity.Avatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvatarQueryResponse {

    private byte[] avatarData;

    public static AvatarQueryResponse from(Avatar avatar) {
        return new AvatarQueryResponse(
                avatar.getAvatarData()
        );
    }
}
