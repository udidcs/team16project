package com.example.team16project.controller.article;

import com.example.team16project.dto.article.response.ArticleImageDto;
import com.example.team16project.service.article.ArticleImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Tag(name = "Article", description = "게시글 이미지 API")
@RequiredArgsConstructor
@Controller
public class ArticleImageController {

    private final ArticleImageService imageService;

    @ResponseBody
    @PostMapping("/article/image/save")
    public String fileWrite(@RequestBody MultipartFile file) throws IOException {
        ArticleImageDto imageDto = imageService.fileWrite(file);
        return imageDto.getFileName();
    }

    @ResponseBody
    @GetMapping("/article/image")
    public byte[] printEditorImage(@RequestParam(name = "filename") String fileName) {

        String path = System.getProperty("user.dir") + "/src/main/resources/static/images/article/image";
        File uploadedFile = new File(path+'/'+fileName);

        if (uploadedFile.exists() == false) {
            throw new RuntimeException();
        }
        try {
            byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());
            return imageBytes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
