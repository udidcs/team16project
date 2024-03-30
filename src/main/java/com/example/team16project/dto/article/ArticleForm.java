package com.example.team16project.dto.article;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.security.Principal;

@Data
public class ArticleForm {

    @Length(max = 50, message = "제목의 길이는 50자 이하여야 합니다")
    private String title;
    @Length(max = 3000, message = "내용의 길이는 3000자 이하여야 합니다")
    private String contents;

    public static Article toEntity(ArticleForm articleForm) {

        return new Article().builder()
                .contents(articleForm.getContents()).title(articleForm.getTitle()).build();
    }
}
