package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.IArticleService;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation("根据文章ID查询")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    @GetMapping("{articleId}")
    public Result<Article> articleByArticleId(@PathVariable("articleId") Long articleId) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getById(articleId));
    }

    @ApiOperation("根据文章分类ID查询")
    @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true)
    @PostMapping("{categoryId}/category")
    public Result<IPage<Article>> articleListByCategoryId(@RequestBody Page<Article> page, @PathVariable("categoryId") Long categoryId) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getArticlePage(page, categoryId));
    }

    @ApiOperation("文章分页查询")
    @PostMapping
    public Result<IPage<Article>> articleListByCategoryId(@RequestBody Page<Article> page) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getArticlePage(page, null));
    }

}

