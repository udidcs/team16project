package com.example.team16project.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleImageDto {

    private String id;
    private String fileName;
    private String filePath;
}
