package com.mingles.metamingle.avatar.command.application.service;

import com.mingles.metamingle.avatar.command.application.dto.response.AvatarCommandResponse;
import com.mingles.metamingle.avatar.command.domain.aggregate.entity.Avatar;
import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import com.mingles.metamingle.avatar.command.domain.repository.AvatarCommandRepository;
import com.mingles.metamingle.avatar.query.infrastructure.repository.AvatarQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvatarCommandService {

    private final AvatarCommandRepository avatarCommandRepository;
    private final AvatarQueryRepository avatarQueryRepository;

    @Transactional
    public AvatarCommandResponse createAvatar(String avatarData, Long memberNo) {

        Optional<Avatar> avatar = avatarQueryRepository.findByAvatarMemberNoVO(new AvatarMemberNoVO(memberNo));

        Avatar createdAvatar;

        if(avatar.isEmpty()) {
            createdAvatar = Avatar.builder()
                    .avatarMemberNoVO(new AvatarMemberNoVO(memberNo))
                    .avatarData(avatarData)
                    .build();

            avatarCommandRepository.save(createdAvatar);

        }
        else {
            createdAvatar = avatar.get();
            createdAvatar.update(avatarData);

        }
        return AvatarCommandResponse.from(createdAvatar);
    }

}