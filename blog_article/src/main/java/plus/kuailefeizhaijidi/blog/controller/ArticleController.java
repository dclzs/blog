package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.service.IArticleService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@Api(tags = "文章接口")
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
        return Result.success(articleService.getOne(select().eq(Article::getArticleId, articleId)));
    }

    @ApiOperation("根据文章分类ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true),
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据")})
    @GetMapping("{categoryId}/category")
    public Result<IPage<Article>> articleListByCategoryId(@PathVariable("categoryId") Long categoryId,
                                                          @RequestParam(value = "current", defaultValue = "1") Integer current,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return Result.success(articleService.page(new Page<>(current, size), list().eq(Article::getCategoryId, categoryId)));
    }

    @ApiOperation("文章分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据")})
    @GetMapping
    public Result<IPage<Article>> articleListByCategoryId(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return Result.success(articleService.page(new Page<>(current, size), list()));
    }

    @ApiOperation("文章ID查询")
    @ApiImplicitParam(name = "ids", value = "文章ID数组")
    @PostMapping
    public Result<List<Article>> articleListByIds(@RequestParam("ids") List<String> ids) {
        if (ids.size() > Constant.MAX_SIZE || ids.isEmpty()) {
            return Result.success(Collections.emptyList());
        }
        Map<Long, Article> articleMap = articleService.list(list().in(Article::getArticleId, ids)).stream().collect(Collectors.toMap(Article::getArticleId, t -> t));
        return Result.success(ids.stream().map(id -> articleMap.get(Long.parseLong(id))).collect(Collectors.toList()));
    }

    private LambdaQueryWrapper<Article> list(){
        return Wrappers.<Article>lambdaQuery()
                .select(Article::getArticleId,
                        Article::getArticleTitle,
                        Article::getArticleDesc,
                        Article::getArticleAuthor,
                        Article::getCreateTime,
                        Article::getUpdateTime)
                .eq(Article::getPublicStatus, Constant.ENABLE)
                .orderByDesc(Article::getCreateTime);
    }


    private LambdaQueryWrapper<Article> select(){
        return Wrappers.<Article>lambdaQuery()
                .eq(Article::getPublicStatus, Constant.ENABLE);
    }
}

