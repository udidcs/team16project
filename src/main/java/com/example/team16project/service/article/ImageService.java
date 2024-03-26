package com.example.team16project.service.article;


import com.example.team16project.dto.article.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageDto fileWrite(MultipartFile file) throws IOException;
}
