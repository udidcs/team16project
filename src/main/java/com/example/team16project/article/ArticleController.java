package com.example.team16project.article;

import com.example.team16project.reply.Reply;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "게시글 CRUD", description = "Response Estimate API")
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "게시글 전체 보기", description = "페이지 번호와 함께 게시글 전체를 볼 수 있습니다.")
    @Parameter(name = "page", description = "페이지 번호", example = "2")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "요청에 성공했습니다.", content = @Content(mediaType = "text/html"))
    })
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
        model.addAttribute("article", Article.builder().articleId(1).title("111")
                .contents("123123").viewCount(4).createdAt(Timestamp.valueOf("2024-04-23 23:11:22")).updatedAt(null).likeCount(5).build());
        model.addAttribute("replys", new ArrayList<Reply>());

        return "article/detail";
    }

    @GetMapping("/article/form")
    public String form(Model model){

        return "article/form";
    }


}
