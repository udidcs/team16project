package com.example.team16project.controller;

import com.example.team16project.article.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest            // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc    // MockMvc 생성 및 자동 구성
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ArticleRepository articleRepository;

    @DisplayName("게시글 목록 조회에 성공한다")
    @Test
    public void getAllArticles() throws Exception {
        // given
        String url = "/articles";

        // when
        ResultActions perform = mockMvc.perform(get(url));

        // then
        perform.andExpect(status().isOk())
        .andExpect(view().name("article/articles"));
    }

}