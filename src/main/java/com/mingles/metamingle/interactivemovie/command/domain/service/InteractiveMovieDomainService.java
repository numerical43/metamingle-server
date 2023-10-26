package com.mingles.metamingle.interactivemovie.command.domain.service;

import org.springframework.stereotype.Service;

@Service
public class InteractiveMovieDomainService {

    public String checkAndGetFileExtension(String fileName) {

        // 허용되는 이미지 확장자 목록
        String allowedExtension = "mp4";

        // 파일의 확장자 추출
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        // 확장자가 없는 경우 예외 처리
        if (fileExtension.length() == 0) {
            throw new IllegalArgumentException("동영상 파일이 아닙니다.");
        }

        // 허용되는 확장자와 비교하여 이미지 파일 여부 확인
        if (allowedExtension.equals(fileExtension.toLowerCase())) {
            throw new IllegalArgumentException("동영상 파일이 아닙니다.");
        }

        return fileExtension;
    }

}
