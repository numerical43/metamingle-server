package com.mingles.metamingle.shortform.command.application.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.DeleteShortFormResponse;
import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.command.domain.repository.ShortFormCommandRepository;
import com.mingles.metamingle.shortform.command.domain.service.ShortFormCommandDomainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    // 숏폼 생성
    @Transactional
    public CreateShortFormResponse createShortForm(MultipartFile file, String title, String description) throws IOException, JCodecException {
        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(firebaseConfigPath));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobId blobId = BlobId.of(bucketName, fileKeyName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("video/mp4").build();

        InputStream inputStream = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        Blob blob = storage.create(blobInfo, bytes);
        inputStream.close();

        String url = bucketUrl + fileKeyName + "?alt=media";

        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);

        ShortForm shortFormEntity= new ShortForm(title, thumbnailUrl, url, description, new Date(),
                                                Boolean.FALSE, null);

        ShortForm uploadedShortForm = shortFormCommandRepository.save(shortFormEntity);

        deleteTempFile();

        return new CreateShortFormResponse(uploadedShortForm.getShortFormNo(),
                uploadedShortForm.getThumbnailUrl(),
                uploadedShortForm.getUrl());
    }

    // 숏폼 삭제
    public DeleteShortFormResponse deleteShortForm(Long shortFormNo) {

        ShortForm shortForm = shortFormCommandRepository.findById(shortFormNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 숏폼입니다."));

        // 사용자 확인 로직 (본인 확인)

        shortFormCommandRepository.delete(shortForm);

        return new DeleteShortFormResponse(shortFormNo);
    }

    // 인터랙티브 무비와 관련된 숏폼 생성
    @Transactional
    public Long createShortFormWithInterativeMovie(MultipartFile file, String title, String description) throws IOException, JCodecException {
        String fileKeyName = createFileName(file.getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(firebaseConfigPath));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobId blobId = BlobId.of(bucketName, fileKeyName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("video/mp4").build();

        InputStream inputStream = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        Blob blob = storage.create(blobInfo, bytes);
        inputStream.close();

        String url = bucketUrl + fileKeyName + "?alt=media";

        String thumbnailUrl = createAndUploadThumbnail(file, fileKeyName);

        ShortForm shortFormEntity= new ShortForm(title, thumbnailUrl, url, description, new Date(),
                Boolean.TRUE, null);

        ShortForm uploadedShortForm = shortFormCommandRepository.save(shortFormEntity);

        deleteTempFile();

        return uploadedShortForm.getShortFormNo();
    }

    // 이미지 파일 이름 생성
    private String createFileName(String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        // UUID + timestamp로 고유한 파일 이름 생성해서 반환
        return UUID.randomUUID().toString().concat(timestamp)
                .concat(shortFormCommandDomainService.checkAndGetFileExtension(fileName));
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
        Storage storage = StorageClient.getInstance().bucket(bucketName).getStorage();
        String thumbnailKey = "thumbnails/" + fileKeyName.replace(".mp4", ".jpeg");

        BlobId blobId = BlobId.of(bucketName, thumbnailKey);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        Blob thumbnailBlob = storage.create(blobInfo, thumbnailBytes);

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


