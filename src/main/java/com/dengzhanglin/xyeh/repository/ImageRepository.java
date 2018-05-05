package com.dengzhanglin.xyeh.repository;

import com.dengzhanglin.xyeh.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageRepository {

    private ImageMapper imageMapper;

    @Autowired
    public ImageRepository(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

}
