package com.mingles.metamingle.interativemovie.command.application.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import com.mingles.metamingle.interativemovie.command.application.dto.response.CreateInteractiveMovieResponse;
import com.mingles.metamingle.interativemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interativemovie.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.interativemovie.command.domain.repository.InteractiveMovieCommandRepository;
import com.mingles.metamingle.interativemovie.command.domain.service.InteractiveMovieDomainService;
import com.mingles.metamingle.interativemovie.command.infrastructure.service.ApiShortFormService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InteractiveMovieCommandService {

    @Value("${firebase.credentials.path}")
    private String firebaseConfigPath; // Firebase Admin SDK 설정 파일 경로
    @Value("${firebase.storage.bucket}")
    private String bucketName; // Firebase Storage 버킷 이름
    @Value("${firebase.storage.bucket-url}")
    private String bucketUrl;


    private final InteractiveMovieCommandRepository interactiveMovieCommandRepository;
    private final InteractiveMovieDomainService interactiveMovieDomainService;
    private final ApiShortFormService apiShortFormService;

    public List<CreateInteractiveMovieResponse> createInteractiveMovie(MultipartFile[] files, String[] titles, String[] descriptions)
            throws JCodecException, IOException {

        List<CreateInteractiveMovieResponse> response = new ArrayList<>();

        Long shortFormNo = apiShortFormService.createShortFormWithInteractiveMovie(files[0], titles[0], descriptions[0]);
        ShortFormNoVO shortFormNoVO = new ShortFormNoVO(shortFormNo);

        for (int i = 1; i <= 2; i++) {

            String fileKeyName = createFileName(files[i].getOriginalFilename()); // 파일 이름을 고유한 파일 이름으로 교체

            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(firebaseConfigPath));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            BlobId blobId = BlobId.of(bucketName, fileKeyName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("video/mp4").build();

            InputStream inputStream = files[i].getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            Blob blob = storage.create(blobInfo, bytes);
            inputStream.close();

            String url = bucketUrl + fileKeyName + "?alt=media";

            String thumbnailUrl = createAndUploadThumbnail(files[i], fileKeyName);

            InteractiveMovie interactiveMovieEntity = new InteractiveMovie(titles[i], thumbnailUrl, url, descriptions[i], new Date(),
                    i, shortFormNoVO, null);

            InteractiveMovie uploadedInteractiveMovie = interactiveMovieCommandRepository.save(interactiveMovieEntity);

            response.add(new CreateInteractiveMovieResponse(null, uploadedInteractiveMovie.getInteractiveMovieNo(),
                                                            uploadedInteractiveMovie.getUrl(),
                                                            uploadedInteractiveMovie.getThumbnailUrl(),
                                                            uploadedInteractiveMovie.getOrder()));
        }

        return response;

    }


    // 이미지 파일 이름 생성
    private String createFileName(String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        // UUID + timestamp로 고유한 파일 이름 생성해서 반환
        return UUID.randomUUID().toString().concat(timestamp)
                .concat(interactiveMovieDomainService.checkAndGetFileExtension(fileName));
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
