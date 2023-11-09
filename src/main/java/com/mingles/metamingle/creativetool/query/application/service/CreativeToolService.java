package com.mingles.metamingle.creativetool.query.application.service;

import com.mingles.metamingle.creativetool.document.Image;
import com.mingles.metamingle.creativetool.document.Sound;
import com.mingles.metamingle.creativetool.query.application.dto.request.ImageRequest;
import com.mingles.metamingle.creativetool.query.application.dto.request.SoundRequest;
import com.mingles.metamingle.creativetool.query.application.dto.response.ImageResponse;
import com.mingles.metamingle.creativetool.query.application.dto.response.SoundResponse;
import com.mingles.metamingle.creativetool.query.domain.mongo.ImageRepository;
import com.mingles.metamingle.creativetool.query.domain.mongo.SoundRepository;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.member.query.application.dto.request.FindMemberRequest;
import com.mingles.metamingle.member.query.application.dto.response.MemberQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreativeToolService {

    private final ImageRepository imageRepository;
    private final SoundRepository soundRepository;

    @Transactional(readOnly = true)
    public List<ImageResponse> findImagesByLocation(ImageRequest request) {

        List<Image> images = imageRepository.findImagesByLocation(request.getLocation());

        /*  전체 조회
        List<ImageResponse> imageResponses = new ArrayList<>();

        for (Image image : images) {
            imageResponses.add(new ImageResponse(image));
        }

        return imageResponses;
        */

        
        //5개 랜덤 추출
        Collections.shuffle(images);

        int numberOfImagesToReturn = Math.min(5, images.size());
        List<ImageResponse> imageResponses = new ArrayList<>();

        for (int i = 0; i < numberOfImagesToReturn; i++) {
            imageResponses.add(new ImageResponse(images.get(i)));
        }

        return imageResponses;
        
    }

    @Transactional(readOnly = true)
    public List<SoundResponse> findSoundsByGenre(SoundRequest request) {

        List<Sound> sounds = soundRepository.findSoundsByGenre(request.getGenre());

        List<SoundResponse> soundResponses = new ArrayList<>();

        for (Sound sound : sounds) {
            soundResponses.add(new SoundResponse(sound));
        }

        return soundResponses;

    }
}
