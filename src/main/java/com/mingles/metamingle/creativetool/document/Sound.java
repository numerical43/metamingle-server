package com.mingles.metamingle.creativetool.document;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@Document(collection = "sounds")
public class Sound {

    @Id
    private String id;

    @Field(name = "genre")
    private String genre;

    @Field(name = "mood")
    private String mood;

    @Field(name = "prompt")
    private String prompt;

    @Field(name = "sound_url")
    private String soundUrl;
}
