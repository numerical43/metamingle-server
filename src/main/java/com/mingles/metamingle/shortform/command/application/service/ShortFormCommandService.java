//package com.mingles.metamingle.shortform.command.application.service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
//import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
//import com.mingles.metamingle.shortform.command.domain.repository.ShortFormCommandRepository;
//import com.mingles.metamingle.shortform.command.domain.service.ShortFormCommandDomainService;
//import lombok.RequiredArgsConstructor;
//import org.jcodec.api.FrameGrab;
//import org.jcodec.api.JCodecException;
//import org.jcodec.common.io.NIOUtils;
//import org.jcodec.common.model.Picture;
//import org.jcodec.scale.AWTUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class ShortFormCommandService {
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//
//    private final ShortFormCommandRepository shortFormCommandRepository;
//
//    private final ShortFormCommandDomainService shortFormCommandDomainService;
//
//    private final AmazonS3 amazonS3;
//
//
//    // 여러개의 동영상 s3에 저장
//
//    // s3에 있는 동영상 삭제
//
//    // 숏폼 생성
//    public CreateShortFormResponse createShortForm(MultipartFile file, String title) throws IOException {
//
//        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체
//
//        InputStream inputStream = file.getInputStream();
//
//        ObjectMetadata objectMetadata = new ObjectMetadata(); // 메타데이터 설정
//        objectMetadata.setContentType("video/mp4");
//        objectMetadata.setContentLength(file.getSize());
//
//        amazonS3.putObject(new PutObjectRequest(bucket, fileKeyName, inputStream, objectMetadata)); // s3에 저장
//
//        String url =  amazonS3.getUrl(bucket, fileKeyName).toString(); // url 반환받기
//        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);
//
//        ShortForm shortForm = new ShortForm(title, thumbnailUrl, url, new Date(), false, null); // ShortForm Entity 생성
//        ShortForm uploadedShortForm = shortFormCommandRepository.save(shortForm);
//
//        return new CreateShortFormResponse(uploadedShortForm.getShortFormNo(), uploadedShortForm.getThumbnailUrl(), uploadedShortForm.getUrl());
//    }
//
//    // 이미지 파일 이름 생성
//    private String createFileName(String fileName) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String timestamp = dateFormat.format(new Date());
//
//        // UUID + timestamp로 고유한 파일 이름 생성해서 반환
//        return UUID.randomUUID().toString().concat(timestamp)
//                .concat(shortFormCommandDomainService.checkAndGetFileExtension(fileName));
//    }
//
//    // 파일 확장자 가져오기
//    private String getFileExtension(String fileName) {
//        int lastDotIndex = fileName.lastIndexOf('.');
//        if (lastDotIndex != -1) {
//            return fileName.substring(lastDotIndex);
//        }
//        return "";
//    }
//
//
//    private String createAndUploadThumbnail(MultipartFile file, String fileKeyName) {
//        try {
//
//            // MultipartFile을 File로 변환
//            File videoFile = multipartToFile(file);
//
//            // 썸네일 생성 (여기서는 첫 프레임을 썸네일로 사용)
//            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(videoFile));
//            grab.seekToSecondPrecise(0); // 첫 프레임으로 이동
//            Picture picture = grab.getNativeFrame();
//            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
//
//            // 썸네일 이미지를 ByteArrayOutputStream에 저장
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "jpeg", baos);
//            byte[] thumbnailBytes = baos.toByteArray();
//
//            // 썸네일 이미지를 AWS S3에 업로드
//            String thumbnailKey = "thumbnails/" + fileKeyName.replace(".mp4", ".jpeg");
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType("image/jpeg");
//
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, thumbnailKey, new ByteArrayInputStream(thumbnailBytes), metadata);
//            amazonS3.putObject(putObjectRequest);
//
//            return amazonS3.getUrl(bucket, thumbnailKey).toString();
//
//        } catch (IOException | JCodecException e) {
//            e.printStackTrace();
//            return ""; // 업로드 실패 시 빈 문자열 또는 오류 처리 방법을 선택합니다.
//        }
//    }
//
//    // MultipartFile을 File로 변환
//    private File multipartToFile(MultipartFile multipart) throws IOException {
//        File file = new File(multipart.getOriginalFilename());
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            StreamUtils.copy(multipart.getInputStream(), fos);
//        }
//        return file;
//    }
//
//    // 숏폼 삭제
//
//    // 인터랙티브 무비 : 숏폼 생성
//
//}
