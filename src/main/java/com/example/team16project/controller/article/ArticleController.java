package com.example.team16project.controller.article;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.article.ArticleDto;
import com.example.team16project.dto.article.ArticleForm;
import com.example.team16project.repository.user.UserRepository;
import com.example.team16project.service.article.ArticleService;
import com.example.team16project.domain.article.Article;
import com.example.team16project.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@Tag(name = "Article", description = "게시글 API")
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final UserRepository userRepository;

    @Operation(summary = "게시글 전체 보기", description = "페이지 번호와 함께 게시글 전체를 볼 수 있습니다")
    @Parameter(name = "page", description = "페이지 번호", example = "2")
    @ApiResponse(responseCode = "200", description = "요청에 성공했습니다", content = @Content(mediaType = "text/html"))
    @GetMapping("/articles")
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

    @GetMapping("/article")
    public String detail(@RequestParam(value = "id", required = true) Long articleId, Model model){
        ArticleDto article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        model.addAttribute("replys", article.getReplys());
        return "article/detail";
    }

    @Operation(summary = "게시글 작성하는 폼", description = "게시글을 작성할 수 있는 폼을 불러옵니다")
    @ApiResponse(responseCode = "200", description = "요청에 성공했습니다", content = @Content(mediaType = "text/html"))
    @GetMapping("/article/form")
    public String getForm(Model model){
        return "article/form";
    }

    @Operation(summary = "게시글 작성하는 폼", description = "게시글을 작성할 수 있는 폼을 불러옵니다")
    @ApiResponse(responseCode = "200", description = "요청에 성공했습니다", content = @Content(mediaType = "text/html"))

    @ResponseBody
    @PostMapping("/article")
    public String saveForm(@Valid @RequestBody ArticleForm articleForm, Principal principal, Model model) throws AuthenticationException {

        if (principal == null)
            throw new AuthenticationException("로그인이 필요합니다");

        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        Article article = articleService.saveArticle(articleForm, user);
        return "/article?id=" + article.getArticleId();
    }
}
