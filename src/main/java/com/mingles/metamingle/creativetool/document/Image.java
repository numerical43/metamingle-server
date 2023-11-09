package com.mingles.metamingle.creativetool.document;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@Document(collection = "images")
public class Image {

    @Id
    private String id;

    @Field(name = "location")
    private String location;

    @Field(name = "technique")
    private String technique;

    @Field(name = "prompt")
    private String prompt;

    @Field(name = "image_url")
    private String imageUrl;
}
