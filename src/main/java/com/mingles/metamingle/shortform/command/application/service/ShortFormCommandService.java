package com.mingles.metamingle.shortform.command.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.mingles.metamingle.shortform.command.domain.repository.ShortFormCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class ShortFormCommandService {

//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;

    private final ShortFormCommandRepository shortFormCommandRepository;

    private final AmazonS3 amazonS3;



    // 여러개의 동영상 s3에 저장

    // s3에 있는 동영상 삭제

    // 숏폼 생성

    // 숏폼 삭제

    // 숏폼 수정

    // 인터랙티브 무비 : 숏폼 생성

}
