package com.mingles.metamingle.creativetool.query.application.service;

import com.mingles.metamingle.creativetool.document.Image;
import com.mingles.metamingle.creativetool.document.Sound;
import com.mingles.metamingle.creativetool.query.application.dto.response.ImageResponse;
import com.mingles.metamingle.creativetool.query.application.dto.response.SoundResponse;
import com.mingles.metamingle.creativetool.query.domain.mongo.ImageRepository;
import com.mingles.metamingle.creativetool.query.domain.mongo.SoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CreativeToolService {

    private final ImageRepository imageRepository;
    private final SoundRepository soundRepository;

    @Transactional(readOnly = true)
    public List<ImageResponse> findImagesByLocation(String location) {

        /* 기법 별 1개씩 총 6개 리턴 */
        List<String> techniques = Arrays.asList("3D", "cartoon", "game cg", "oil painting", "painting", "photograph");

        List<ImageResponse> imageResponses = new ArrayList<>();

//        Random random = new Random();
//        int randomNumber = random.nextInt(6);

        for (String technique : techniques) {
            int i = 0;
            List<Image> images = imageRepository.findImagesByLocationAndTechnique(location, technique);
            if (!images.isEmpty()) {
                imageResponses.add(new ImageResponse(images.get(i++)));
            }
        }


        //        List<Image> images = imageRepository.findImagesByLocation(location);

        /*  전체 조회
        List<ImageResponse> imageResponses = new ArrayList<>();

        for (Image image : images) {
            imageResponses.add(new ImageResponse(image));
        }

        return imageResponses;
        */

        
        //5개 랜덤 추출
//        Collections.shuffle(images);
//
//        int numberOfImagesToReturn = Math.min(5, images.size());
//        List<ImageResponse> imageResponses = new ArrayList<>();
//
//        for (int i = 0; i < numberOfImagesToReturn; i++) {
//            imageResponses.add(new ImageResponse(images.get(i)));
//        }

        return imageResponses;
        
    }

    @Transactional(readOnly = true)
    public List<SoundResponse> findSoundsByGenre(String mood) {

        List<Sound> sounds = soundRepository.findSoundsByMood(mood);

        /* 전체 조회
        List<SoundResponse> soundResponses = new ArrayList<>();

        for (Sound sound : sounds) {
            soundResponses.add(new SoundResponse(sound));
        }

        return soundResponses;
        */

        Collections.shuffle(sounds);

        int numberOfSoundsToReturn = Math.min(3, sounds.size());
        List<SoundResponse> soundResponses = new ArrayList<>();

        for (int i = 0; i < numberOfSoundsToReturn; i++) {
            soundResponses.add(new SoundResponse(sounds.get(i)));
        }

        return soundResponses;

    }
}
