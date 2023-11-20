package com.mingles.metamingle.shortform.command.application.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import com.mingles.metamingle.shortform.command.application.dto.response.SubtitledVideo;
import com.mingles.metamingle.shortform.command.application.dto.response.UploadVideo;
import com.mingles.metamingle.shortform.command.domain.aggregate.vo.MemberNoVO;
import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.DeleteShortFormResponse;
import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.command.domain.repository.ShortFormCommandRepository;
import com.mingles.metamingle.shortform.command.domain.service.ShortFormCommandDomainService;
import com.mingles.metamingle.shortform.command.infrastructure.service.ApiInteractiveMovieCommandService;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ShortFormFirebaseService {

    @Value("${firebase.credentials.path}")
    private String firebaseConfigPath; // Firebase Admin SDK 설정 파일 경로
    @Value("${firebase.storage.bucket}")
    private String bucketName; // Firebase Storage 버킷 이름
    @Value("${firebase.storage.bucket-url}")
    private String bucketUrl;

    private final ShortFormCommandRepository shortFormCommandRepository;

    private final ShortFormCommandDomainService shortFormCommandDomainService;

    private final ApiInteractiveMovieCommandService apiInteractiveMovieCommandService;

//    // 숏폼 생성
//    @Transactional
//    public CreateShortFormResponse createShortForm(MultipartFile file, String title, String description, Long memberNo) throws IOException, JCodecException {
//        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체
//
//        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
//        InputStream inputStream = file.getInputStream();
//        Blob blob = bucket.create(fileKeyName, inputStream, file.getContentType());
//        Blob getBlob = bucket.get(fileKeyName);
//
//        System.out.println("blob shortForm = " + getBlob.getMediaLink());
//
//
//        inputStream.close();
//
//        String url = bucketUrl + fileKeyName + "?alt=media";
//
//        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);
//
//        MemberNoVO memberNoVO = new MemberNoVO(memberNo);
//
//        ShortForm shortFormEntity = new ShortForm(title, thumbnailUrl, url, description, new Date(),
//                                                Boolean.FALSE, memberNoVO, null);
//
//        ShortForm uploadedShortForm = shortFormCommandRepository.save(shortFormEntity);
//
//        deleteTempFile();
//
//        return new CreateShortFormResponse(uploadedShortForm.getShortFormNo(),
//                uploadedShortForm.getThumbnailUrl(),
//                uploadedShortForm.getUrl());
//    }

    // 숏폼 생성
    public UploadVideo createShortForm(MultipartFile file, String fileKeyName) throws IOException, JCodecException {

        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
        InputStream inputStream = file.getInputStream();
        Blob blob = bucket.create(fileKeyName, inputStream, file.getContentType());
        Blob getBlob = bucket.get(fileKeyName);

        System.out.println("blob shortForm = " + getBlob.getMediaLink());

        inputStream.close();

        String url = bucketUrl + fileKeyName + "?alt=media";
        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);

        deleteTempFile();

        return new UploadVideo(url, thumbnailUrl);
    }

    // ai 서버 자막
    @Transactional
    public CreateShortFormResponse createShortFormWithSubtitle(MultipartFile file, String title, String description,
                                                               Long memberNo, Boolean isInteractive)
            throws IOException, JCodecException, InterruptedException {

        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

        SubtitledVideo subtitledVideo = new SubtitledVideo();
        // ai에 보내서 영어 자막 영상 요청 (fileKeyName과 MultipartFile을 전달)
        subtitledVideo.setFileEng(file);
        Thread.sleep(3000); // 3초 sleep
        // ai에 보내서 한글 자막 영상 요청 (fileKeyName 전달)
        subtitledVideo.setFileKr(file);

        // 영어 자막 동영상 생성
        UploadVideo uploadVideoEng = createShortForm(subtitledVideo.getFileEng(), fileKeyName + "eng.mp4");
        // 한글 자막 동영상 생성
        UploadVideo uploadVideoKr = createShortForm(subtitledVideo.getFileKr(), fileKeyName + "kr.mp4");


        MemberNoVO memberNoVO = new MemberNoVO(memberNo);

        ShortForm shortForm = ShortForm.builder()
                .title(title)
                .description(description)
                .memberNoVO(memberNoVO)
                .urlKr(uploadVideoKr.getUrl())
                .thumbnailUrlKr(uploadVideoKr.getThumbnailUrl())
                .urlEng(uploadVideoEng.getUrl())
                .thumbnailUrlEng(uploadVideoEng.getThumbnailUrl())
                .date(new Date())
                .isInteractive(isInteractive)
                .build();

        ShortForm createdShortForm = shortFormCommandRepository.save(shortForm);

        return new CreateShortFormResponse(createdShortForm.getShortFormNo(), createdShortForm.getThumbnailUrlKr(),
                                           createdShortForm.getUrlKr(), createdShortForm.getThumbnailUrlEng(),
                                           createdShortForm.getUrlEng());
    }

    // 숏폼 삭제
    public DeleteShortFormResponse deleteShortForm(Long shortFormNo) {

        ShortForm shortForm = shortFormCommandRepository.findById(shortFormNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 숏폼입니다."));

        List<Long> deletedInteractiveMovieNos = null;

        if (shortForm.getIsInteractive()) {
            deletedInteractiveMovieNos = apiInteractiveMovieCommandService.deleteInteractiveMovieWithShortFormNo(shortFormNo);
        }

        // 사용자 확인 로직 (본인 확인)

        // firebase storage의 영상, 썸네일 삭제
        String thumbnailNameKr = shortForm.getThumbnailUrlKr()
                                        .replace(bucketUrl, "")
                                        .replace("%2F", "/")
                                        .replace("?alt=media", "");
        String videoNameKr = shortForm.getUrlKr()
                                    .replace(bucketUrl, "")
                                    .replace("?alt=media", "");
        String thumbnailNameEng = shortForm.getThumbnailUrlEng()
                .replace(bucketUrl, "")
                .replace("%2F", "/")
                .replace("?alt=media", "");
        String videoNameEng = shortForm.getUrlEng()
                .replace(bucketUrl, "")
                .replace("?alt=media", "");

        BlobId blobIdThumbnailKr = BlobId.of(bucketName, thumbnailNameKr);
        BlobId blobIdVideoKr = BlobId.of(bucketName, videoNameKr);
        BlobId blobIdThumbnailEng = BlobId.of(bucketName, thumbnailNameEng);
        BlobId blobIdVideoEng = BlobId.of(bucketName, videoNameEng);

        Storage storage = StorageClient.getInstance().bucket(bucketName).getStorage();
        Bucket bucket = StorageClient.getInstance().bucket(bucketName);

        if (!storage.delete(blobIdThumbnailKr)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "숏폼 썸네일 삭제 실패 ");
        }

        if (!storage.delete(blobIdThumbnailEng)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "숏폼 썸네일 삭제 실패 ");
        }

        if (!storage.delete(blobIdVideoKr)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "숏폼 무비 영상 삭제 실패 ");
        }

        if (!storage.delete(blobIdVideoEng)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "숏폼 무비 영상 삭제 실패 ");
        }


        shortFormCommandRepository.delete(shortForm);

        return new DeleteShortFormResponse(shortFormNo, deletedInteractiveMovieNos);
    }


//    // 인터랙티브 무비와 관련된 숏폼 생성
//    @Transactional
//    public ShortForm createShortFormWithInteractiveMovie(MultipartFile file, String title, String description, Long memberNo) throws IOException, JCodecException {
//        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체
//
//        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
//        InputStream inputStream = file.getInputStream();
//        Blob blob = bucket.create(fileKeyName, inputStream, file.getContentType());
//
//        System.out.println("blob interactiveMovie = " + blob.getMediaLink());
//
//        inputStream.close();
//
//        String url = bucketUrl + fileKeyName + "?alt=media";
//
//        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);
////        // 임시 멤버 넘버 사용
//        MemberNoVO memberNoVO = new MemberNoVO(memberNo);
//
//        ShortForm shortFormEntity= new ShortForm(title, thumbnailUrl, url, description, new Date(),
//                Boolean.TRUE, memberNoVO, null);
//
//        ShortForm uploadedShortForm = shortFormCommandRepository.save(shortFormEntity);
//
//        deleteTempFile();
//
//        return uploadedShortForm;
//    }

    // 인터랙티브 무비와 관련된 숏폼 생성
    @Transactional
    public UploadVideo createShortFormWithInteractiveMovie(MultipartFile file) throws IOException, JCodecException {
        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
        InputStream inputStream = file.getInputStream();
        Blob blob = bucket.create(fileKeyName, inputStream, file.getContentType());

        System.out.println("blob interactiveMovie = " + blob.getMediaLink());

        inputStream.close();

        String url = bucketUrl + fileKeyName + "?alt=media";

        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);

        UploadVideo uploadVideo = new UploadVideo(url, thumbnailUrl);

        deleteTempFile();

        return uploadVideo;
    }

    // 이미지 파일 이름 생성
    private String createFileName(String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        String fileExtension = shortFormCommandDomainService.checkAndGetFileExtension(fileName);

        // UUID + timestamp로 고유한 파일 이름 생성해서 반환
        return UUID.randomUUID().toString().concat(timestamp);
    }

    // 파일 확장자 가져오기
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex);
        }
        return "";
    }

    // 썸네일 이미지 생성
    private String createAndUploadThumbnail(MultipartFile file, String fileKeyName) throws IOException, JCodecException {
        FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(multipartToFile(file)));
        double startSec = 2; // 영상에서 얻고자 하는 시간대 설정

        // 시작 시간으로 이동
        grab.seekToSecondPrecise(startSec);
        Picture picture = grab.getNativeFrame();

        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 이미지 포맷을 "jpeg"로 변경
        ImageIO.write(bufferedImage, "jpeg", baos);
        byte[] thumbnailBytes = baos.toByteArray();
        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
        String thumbnailKey = "thumbnails/" + fileKeyName.replace(".mp4", ".jpeg");
        Blob blob = bucket.create(thumbnailKey, thumbnailBytes, "image/jpeg");

        System.out.println("blob thumbnail = " + blob.getMediaLink());

        return bucketUrl + thumbnailKey.replace("/", "%2F") + "?alt=media";
    }

    // MultipartFile을 File로 변환
    private File multipartToFile(MultipartFile multipart) throws IOException {
        File file = new File(multipart.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            StreamUtils.copy(multipart.getInputStream(), fos);
        }
        return file;
    }

    private void deleteTempFile() {
        String tempFilePath = "C:/User/user/temp";

        File directory = new File(tempFilePath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }
}


