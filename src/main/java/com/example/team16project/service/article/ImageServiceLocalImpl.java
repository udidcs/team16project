package com.example.team16project.service.article;

import com.example.team16project.dto.article.ImageDto;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@NoArgsConstructor
public class ImageServiceLocalImpl implements ImageService {

    private ImageDto imageDto;
    @Override
    public ImageDto fileWrite(MultipartFile file) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources/static/images/article/image";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File savefile = new File(path, fileName);
        file.transferTo(savefile);
        imageDto = new ImageDto("id", fileName, "");

        return imageDto;
    }
}
