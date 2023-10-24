package com.mingles.metamingle.shortform.command.infrastructure.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import com.mingles.metamingle.interativemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interativemovie.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.interativemovie.command.domain.repository.InteractiveMovieCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiInteractiveMovieCommandService {

    @Value("${firebase.storage.bucket-url}")
    private String bucketUrl;

    @Value("${firebase.storage.bucket}")
    private String bucketName;

    private final InteractiveMovieCommandRepository interactiveMovieCommandRepository;

    public List<Long> deleteInteractiveMovieWithShortFormNo(Long shortFormNO) {

        ShortFormNoVO shortFormNoVO = new ShortFormNoVO(shortFormNO);

        List<InteractiveMovie> findInteractiveMovies = interactiveMovieCommandRepository.findAllByShortFormNoVO(shortFormNoVO);

        List<Long> deletedInteractiveMovieNos = null;

        for (InteractiveMovie interactiveMovie : findInteractiveMovies) {

            interactiveMovieCommandRepository.delete(interactiveMovie);
            deletedInteractiveMovieNos.add(interactiveMovie.getInteractiveMovieNo());

            String thumbnailName = interactiveMovie.getThumbnailUrl().replace(bucketUrl, "")
                                                                     .replace("%2F", "/")
                                                                     .replace("?alt=media", "");
            String videoName = interactiveMovie.getUrl().replace(bucketUrl, "")
                                                        .replace("?alt=media", "");

            BlobId blobIdThumbnail = BlobId.of(bucketName, thumbnailName);
            BlobId blobIdVideo = BlobId.of(bucketName, videoName);

            Storage storage = StorageClient.getInstance().bucket(bucketName).getStorage();

            if (!storage.delete(blobIdThumbnail)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "인터랙티브 무비 썸네일 삭제 실패 ");
            }

            if (!storage.delete(blobIdVideo)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "인터랙티브 무비 영상 삭제 실패 ");

            }

        }

        return deletedInteractiveMovieNos;

    }

}
