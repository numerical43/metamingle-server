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

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final ShortFormCommandRepository shortFormCommandRepository;

    private final AmazonS3 amazonS3;


//    // 여러 개의 이미지 s3에 저장
//    public List<ImageCreateResponse> uploadImageFiles(List<MultipartFile> multipartFileList) {
//
//        // s3 bucket 의 폴더 중 user 폴더로 지정
//        String bucketfolder = "user_post/";
//        // s3 bucket에 저장 후 반환된 url을 모아둔 list
//        List<ImageCreateResponse> imageUrls = new ArrayList<>();
//
//        for (MultipartFile multipartFile : multipartFileList) {
//            // 이미지 이름 새로 생성
//            String filename = createFileName(multipartFile.getOriginalFilename());
//
//            try (InputStream inputStream = multipartFile.getInputStream()) {
//                // 이미지 압축
//                InputStream compressedInputStream = compressImage(inputStream);
//
//                ObjectMetadata objectMetadata = new ObjectMetadata();
//                objectMetadata.setContentLength(compressedInputStream.available());
//                objectMetadata.setContentType(multipartFile.getContentType());
//
//                // s3에 저장
//                amazonS3.putObject(new PutObjectRequest(bucket, bucketfolder + filename, compressedInputStream, objectMetadata));
//
//                // url list 저장된 이미지의 url 저장
//                String imageUrl = amazonS3.getUrl(bucket, bucketfolder + filename).toString();
//                imageUrls.add(ImageCreateResponse.from(imageUrl));
//
//            } catch (IOException | SdkClientException e) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//            }
//        }
//
//        return imageUrls;
//
//    }
//
//    // 이미지 s3에 업로드
//    public ImageCreateResponse uploadImageFile(MultipartFile multipartFile, String board) {
//
//        // 이미지 파일 이름 새로 만들기
//        String fileName = createFileName(multipartFile.getOriginalFilename());
//        // s3 bucket 의 폴더 지정
//        String bucketFolder = (board.equals("admin")) ? "admin_post/" : "user_post/";
//
//        // s3에 이미지 저장
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            // 이미지 압축
//            InputStream compressedInputStream = compressImage(inputStream);
//
//            // 이미지 메타데이터 설정
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(compressedInputStream.available());
//            objectMetadata.setContentType(multipartFile.getContentType());
//
//            // amazon s3에 저장
//            amazonS3.putObject( new PutObjectRequest(bucket, bucketFolder + fileName, compressedInputStream, objectMetadata));
//
//            // 저장된 이미지의 url 반환
//            return ImageCreateResponse.from(amazonS3.getUrl(bucket, bucketFolder + fileName).toString());
//
//        } catch (IOException | SdkClientException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//
//    }
//
//    // s3에서 하나의 이미지 삭제
//    public void deleteImageFile(ImageDeleteRequest imageDeleteRequest) {
//
//        // s3 bucket 의 폴더 지정
//        String bucketFolder = (imageDeleteRequest.board().equals("admin")) ? "admin_post/" : "user_post/";
//
//        amazonS3.deleteObject(new DeleteObjectRequest(bucket, imageDeleteRequest.imageUrl().split("/")[3]));
//
//    }
//
//    // 유저 게시판 : 특정 게시물과 관련된 이미지 entity 생성
//    public ImageResponse createUserPostImage(ImageRequest imageRequest) {
//
//        UserPostVO userPostVO = new UserPostVO(imageRequest.postNo());
//
//        // 이미지 entity 저장
//        List<Image> imageEntities = imageRequest.imageUrls().stream()
//                .map(imageUrl -> {
//                    Image image = new Image.Builder()
//                            .imageUrl(imageUrl)
//                            .userPostVO(userPostVO)
//                            .build();
//                    return imageCommandRepository.save(image);
//                })
//                .toList();
//
//        // 생성한 이미지 entity 들의 imageNo만 모아서 반환
//        List<Long> imageNos = imageEntities.stream()
//                .map(Image::getImageNo)
//                .toList();
//
//        return new ImageResponse(imageNos);
//    }
//
//    // 유저 게시판 : 특정 게시물과 관련된 s3이미지, 이미지 entity 전부 삭제
//    public void deleteUserPostImage(ImageDeletePostRequest imageDeletePostRequest) {
//
//        UserPostVO userPostVO = new UserPostVO(imageDeletePostRequest.postNo());
//
//        // 이미지 entity 전부 삭제
//        imageCommandRepository.deleteImagesByUserPostVO(userPostVO);
//
//    }
//
//    // 관리자 게시판 : 특정 게시물과 관련된 이미지 entity 생성
//    public ImageResponse createAdminPostImage(ImageRequest imageRequest) {
//
//        AdminPostVO adminPostVO = new AdminPostVO(imageRequest.postNo());
//
//        // 이미지 entity 저장
//        List<Image> imageEntities = imageRequest.imageUrls().stream()
//                .map(imageUrl -> {
//                    Image image = new Image.Builder()
//                            .imageUrl(imageUrl)
//                            .adminPostVO(adminPostVO)
//                            .build();
//                    return imageCommandRepository.save(image);
//                })
//                .toList();
//
//        List<Long> imageNos = imageEntities.stream()
//                .map(Image::getImageNo)
//                .toList();
//
//        return new ImageResponse(imageNos);
//
//    }
//
//    // 관리자 게시판 : 특정 게시물과 관련된 s3이미지, 이미지 entity 전부 삭제
//    public void deleteAdminPostImage(ImageDeletePostRequest imageDeletePostRequest) {
//
//        AdminPostVO adminPostVO = new AdminPostVO(imageDeletePostRequest.postNo());
//
//        // 이미지 entity 전부 삭제
//        imageCommandRepository.deleteImagesByAdminPostVO(adminPostVO);
//
//    }
//
//    // 이미지 파일 이름 생성
//    private String createFileName(String fileName) {
//
//        LocalDate currentDate = LocalDate.now();
//
//        // 날짜 형식 -> yyyyMMdd
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//
//        // UUID + 오늘 날짜로 고유한 파일 이름 생성해서 반환
//        return UUID.randomUUID().toString().concat(currentDate.format(formatter)).concat(imageCommandDomainService.checkAndGetFileExtension(fileName));
//
//    }

    // 여러개의 동영상 s3에 저장

    // s3에 있는 동영상 삭제

    // 숏폼 생성

    // 숏폼 삭제

    // 숏폼 수정

    // 인터랙티브 무비 : 숏폼 생성

}
