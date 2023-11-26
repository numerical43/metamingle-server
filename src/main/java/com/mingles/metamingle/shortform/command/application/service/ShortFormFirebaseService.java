package com.mingles.metamingle.shortform.command.application.service;

import com.mingles.metamingle.global.infra.AiInfraService;
import com.mingles.metamingle.quiz.command.application.service.QuizCommandService;
import com.mingles.metamingle.scenario.command.application.service.ScenarioCommandService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
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
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ShortFormFirebaseService {

    @Value("${firebase.storage.bucket}")
    private String bucketName; // Firebase Storage 버킷 이름
    @Value("${firebase.storage.bucket-url}")
    private String bucketUrl;

    Duration connectionTimeout = Duration.ofSeconds(120);

    HttpClient httpClient = HttpClient.create()
            .responseTimeout(connectionTimeout);

    ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

    private WebClient webClient;

    //AI 세션 변경 확인
    public void setWebClient(String aiAddr) {
        this.webClient = WebClient.builder().baseUrl(aiAddr).build();
    }



    private final ShortFormCommandRepository shortFormCommandRepository;
    private final ShortFormCommandDomainService shortFormCommandDomainService;
    private final ApiInteractiveMovieCommandService apiInteractiveMovieCommandService;
    private final QuizCommandService quizCommandService;
    private final AiInfraService aiInfraService;

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
    @Transactional
    public UploadVideo createShortForm(MultipartFile file, String fileKeyName, String thumbnailUrl) throws IOException, JCodecException {

        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
        InputStream inputStream = file.getInputStream();
        Blob blob = bucket.create(fileKeyName, inputStream, file.getContentType());
        Blob getBlob = bucket.get(fileKeyName);

        inputStream.close();

        String url = bucketUrl + fileKeyName + "?alt=media";
        System.out.println("shortFormUrl = " + url);

        deleteTempFile();

        return new UploadVideo(url, thumbnailUrl);
    }

    // ai 서버 자막
    @Async
    @Transactional
    public CreateShortFormResponse createShortFormWithSubtitle(byte[] fileBytes, String fileName, String uuid,
                                                               String title, String description,
                                                               Long memberNo, Boolean isInteractive)
            throws IOException, JCodecException {

        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        MultipartFile file = new MockMultipartFile("file", fileName, "video/mp4", inputStream);

        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

        SubtitledVideo subtitledVideo = new SubtitledVideo();

        // ai 서버에 자막 동영상 요청 & 응답 받기
        System.out.println("ai 영어 자막 영상 요청");
        subtitledVideo.setFileEng(sendToAIForEngSub(file.getResource(), fileKeyName));
        System.out.println("ai 영어 자막 영상 응답 완료");
        System.out.println("ai 한글 자막 영상 요청");
        subtitledVideo.setFileKr(sendToAIForKrSub(fileKeyName));
        System.out.println("ai 한글 자막 영상 응답 완료");

        // 썸네일 이미지 생성 (영어자막/한글자막 영상에 관련없이 썸네일은 같음)
        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName + ".jpeg");

        // 영어 자막 동영상 생성
        UploadVideo uploadVideoEng = createShortForm(subtitledVideo.getFileEng(), fileKeyName + "eng.mp4", thumbnailUrl);
        // 한글 자막 동영상 생성
        UploadVideo uploadVideoKr = createShortForm(subtitledVideo.getFileKr(), fileKeyName + "kr.mp4", thumbnailUrl);

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

        System.out.println("숏폼 생성 & 저장 완료");

//        quizCommandService.updateQuizWithUUID(createdShortForm.getShortFormNo(), UUID.fromString(uuid));

        return new CreateShortFormResponse(createdShortForm.getShortFormNo(), createdShortForm.getThumbnailUrlKr(),
                                           createdShortForm.getUrlKr(), createdShortForm.getThumbnailUrlEng(),
                                           createdShortForm.getUrlEng());
    }

    @Transactional
    public CreateShortFormResponse createShortFormWithSubtitleWithInteractiveMovie(byte[] fileBytes, String fileName, String uuid,
                                                                                  String title, String description,
                                                                                  Long memberNo, Boolean isInteractive)
                                                                                  throws IOException, JCodecException {

        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        MultipartFile file = new MockMultipartFile("file", fileName, "video/mp4", inputStream);

        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

        SubtitledVideo subtitledVideo = new SubtitledVideo();

        // ai 서버에 자막 동영상 요청 & 응답 받기
        System.out.println("ai 영어 자막 영상 요청");
        subtitledVideo.setFileEng(sendToAIForEngSub(file.getResource(), fileKeyName));
        System.out.println("ai 영어 자막 영상 응답 완료");
        System.out.println("ai 한글 자막 영상 요청");
        subtitledVideo.setFileKr(sendToAIForKrSub(fileKeyName));
        System.out.println("ai 한글 자막 영상 응답 완료");

        // 썸네일 이미지 생성 (영어자막/한글자막 영상에 관련없이 썸네일은 같음)
        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName + ".jpeg");

        // 영어 자막 동영상 생성
        UploadVideo uploadVideoEng = createShortForm(subtitledVideo.getFileEng(), fileKeyName + "eng.mp4", thumbnailUrl);
        // 한글 자막 동영상 생성
        UploadVideo uploadVideoKr = createShortForm(subtitledVideo.getFileKr(), fileKeyName + "kr.mp4", thumbnailUrl);

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

        inputStream.close();

        return new CreateShortFormResponse(createdShortForm.getShortFormNo(), createdShortForm.getThumbnailUrlKr(),
                createdShortForm.getUrlKr(), createdShortForm.getThumbnailUrlEng(),
                createdShortForm.getUrlEng());
    }

    // 숏폼 삭제
    @Transactional
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

    private MultipartFile dataBufferToMultipartFile(String fileKeyName, Flux<DataBuffer> responseBody) {
        byte[] byteArray = responseBody
                .collectList()
                .map(dataBuffers -> {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        dataBuffers.forEach(buffer -> {
                            byte[] bytes = new byte[buffer.readableByteCount()];
                            buffer.read(bytes);
                            try {
                                outputStream.write(bytes);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            DataBufferUtils.release(buffer);
                        });
                        return outputStream.toByteArray();
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                })
                .block();

        return new MockMultipartFile(fileKeyName, fileKeyName, MediaType.MULTIPART_FORM_DATA_VALUE, byteArray);
    }


    private MultipartFile sendToAIForEngSub(Resource file, String fileKeyName) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", file);
        bodyBuilder.part("file_uuid", fileKeyName);

        System.out.println("영어 자막 영상 처리 중");

        Flux<DataBuffer> responseBody = webClient.post()
                .uri("/mp4/en_script_video/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        return dataBufferToMultipartFile(fileKeyName, responseBody);
    }

    private MultipartFile sendToAIForKrSub(String fileKeyName) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file_uuid", fileKeyName);

        System.out.println("한글 자막 영상 처리 중");

        Flux<DataBuffer> responseBody = webClient.post()
                .uri("/mp4/modi_kr_script_video/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        return dataBufferToMultipartFile(fileKeyName, responseBody);
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
        System.out.println("file = " + file.getSize());
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
        String thumbnailKey = "thumbnails/" + fileKeyName;
        Blob blob = bucket.create(thumbnailKey, thumbnailBytes, "image/jpeg");

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

    // 로컬 파일 삭제
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


