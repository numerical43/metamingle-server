package com.mingles.metamingle.creativetool.query.domain.mongo;

import com.mingles.metamingle.creativetool.document.Sound;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SoundRepository extends MongoRepository<Sound, String> {
    List<Sound> findSoundsByMood(String mood);
}
