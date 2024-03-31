package com.example.team16project.service.article;


import com.example.team16project.dto.article.response.ArticleImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ArticleImageService {
    ArticleImageDto fileWrite(MultipartFile file) throws IOException;
}
