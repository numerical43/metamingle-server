package com.mingles.metamingle.creativetool.query.domain.mongo;

import com.mingles.metamingle.creativetool.document.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findImagesByLocation(String location);
    List<Image> findImagesByLocationAndTechnique(String location, String technique);
}
