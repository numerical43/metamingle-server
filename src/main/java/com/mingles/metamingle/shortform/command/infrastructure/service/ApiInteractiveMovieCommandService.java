package com.mingles.metamingle.shortform.command.infrastructure.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import com.mingles.metamingle.interactivemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interactivemovie.command.domain.aggregate.vo.ShortFormNoVO;
import com.mingles.metamingle.interactivemovie.command.domain.repository.InteractiveMovieCommandRepository;
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

            String thumbnailKrName = interactiveMovie.getThumbnailUrlKr().replace(bucketUrl, "")
                                                                     .replace("%2F", "/")
                                                                     .replace("?alt=media", "");
            String videoKrName = interactiveMovie.getUrlKr().replace(bucketUrl, "")
                                                        .replace("?alt=media", "");

            String thumbnailEngName = interactiveMovie.getThumbnailUrlEng().replace(bucketUrl, "")
                    .replace("%2F", "/")
                    .replace("?alt=media", "");
            String videoEngName = interactiveMovie.getUrlEng().replace(bucketUrl, "")
                    .replace("?alt=media", "");

            BlobId blobIdThumbnailKr = BlobId.of(bucketName, thumbnailKrName);
            BlobId blobIdVideoKr = BlobId.of(bucketName, videoKrName);
            BlobId blobIdThumbnailEng = BlobId.of(bucketName, thumbnailEngName);
            BlobId blobIdVideoEng = BlobId.of(bucketName, videoEngName);

            Storage storage = StorageClient.getInstance().bucket(bucketName).getStorage();

            if (!storage.delete(blobIdThumbnailKr)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "인터랙티브 무비 썸네일 삭제 실패 ");
            }

            if (!storage.delete(blobIdVideoKr)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "인터랙티브 무비 영상 삭제 실패 ");
            }

            if (!storage.delete(blobIdThumbnailEng)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "인터랙티브 무비 썸네일 삭제 실패 ");
            }

            if (!storage.delete(blobIdVideoEng)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "인터랙티브 무비 영상 삭제 실패 ");

            }

        }

        return deletedInteractiveMovieNos;

    }

}
