package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserProfileImageService {

    private final UserRepository userRepository;

    String directory = System.getProperty("user.dir");
    @Value("${profile-path}")
    String subDirectory;

    public byte[] getImage(String filename) throws IOException {


        // 요청된 파일 이름으로 실제 파일의 경로 설정
        Path file = Paths.get(directory + subDirectory).resolve(filename);
        // 이미지 파일을 바이트 배열로 읽어오기
        byte[] imageBytes = Files.readAllBytes(file);
        return imageBytes;
    }

    public String saveImageToFile(MultipartFile pdtimg) {
        try {
            int i =  pdtimg.getOriginalFilename().lastIndexOf('.');
            String substring = pdtimg.getOriginalFilename().substring(i + 1);
            String str = String.valueOf(UUID.randomUUID().toString()) + "." + substring;
            pdtimg.transferTo(new File(directory + subDirectory + "/" + str));
            return str;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void saveImageToDB(String imageFileName, Principal principal) {
        Optional<User> email = userRepository.findByEmail(principal.getName());
        email.get().setProfileImage(imageFileName);
    }
}
