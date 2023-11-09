package com.mingles.metamingle.creativetool.query.application.dto.response;

import com.mingles.metamingle.creativetool.document.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private String location;
    private String imageUrl;

    public ImageResponse(Image image) {
        this.location = image.getLocation();
        this.imageUrl = image.getImageUrl();
    }

}
