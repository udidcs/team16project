package com.example.team16project.controller.article;

import com.example.team16project.dto.article.ArticleDto;
import com.example.team16project.dto.article.ImageDto;
import com.example.team16project.service.article.ArticleService;
import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.reply.Reply;
import com.example.team16project.service.article.ImageService;
import com.example.team16project.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Article", description = "게시글 API")
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ImageService imageService;

    @Operation(summary = "게시글 전체 보기", description = "페이지 번호와 함께 게시글 전체를 볼 수 있습니다")
    @Parameter(name = "page", description = "페이지 번호", example = "2")
    @ApiResponse(responseCode = "200", description = "요청에 성공했습니다", content = @Content(mediaType = "text/html"))
    @GetMapping("/article/articles")
    public String articles(@RequestParam(defaultValue = "1") int page, Model model) {

        if (page < 1) {
            page = 1;
        }

        setPaginationAttributes(model, page,
                articleService.getTotalPages(PaginationUtil.PageSize), articleService.getArticles(page, PaginationUtil.PageSize));

        return "article/articles";
    }

    private void setPaginationAttributes(Model model, int page, int totalPages, List<ArticleDto> list) {

        int startIdx = PaginationUtil.calculateStartIndex(page);
        int endIdx = PaginationUtil.calculateEndIndex(page, totalPages);

        model.addAttribute("articles", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("startIdx", startIdx);
        model.addAttribute("endIdx", endIdx);
        model.addAttribute("totalPages", totalPages);
    }

    @GetMapping("/article/detail")
    public String detail(@RequestParam("id") int articleId, Model model){

        //저장한 댓글 가져오기
        model.addAttribute("article", Article.builder().articleId(1L).title("111")
                .contents("123123").viewCount(4).createdAt(LocalDateTime.now()).updatedAt(null).likeCount(5).build());
        model.addAttribute("replys", new ArrayList<Reply>());
        // to-do : 대댓글
        return "article/detail";
    }


    @Operation(summary = "게시글 작성하는 폼", description = "게시글을 작성할 수 있는 폼을 불러옵니다")
    @ApiResponse(responseCode = "200", description = "요청에 성공했습니다", content = @Content(mediaType = "text/html"))
    @GetMapping("/article/form")
    public String form(Model model){
        return "article/form";
    }

    @ResponseBody
    @PostMapping("/article/image/save")
    public String fileWrite(@RequestBody MultipartFile file) throws IOException {
        ImageDto imageDto = imageService.fileWrite(file);
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
