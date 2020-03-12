package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.IArticleService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true),
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据")})
    @GetMapping("{categoryId}/category")
    public Result<IPage<Article>> articleListByCategoryId(@PathVariable("categoryId") Long categoryId,
                                                          @RequestParam(value = "current", defaultValue = "1") Integer current,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getArticlePage(new Page<>(current, size), categoryId));
    }

    @ApiOperation("文章分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据")})
    @GetMapping
    public Result<IPage<Article>> articleListByCategoryId(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getArticlePage(new Page<>(current, size), null));
    }

    @ApiOperation("文章ID查询")
    @ApiImplicitParam(name = "ids[]", value = "文章ID数组")
    @PostMapping
    public Result<List<Article>> articleListByIds(@RequestParam("ids[]") List<Integer> ids){
        return ids.size() > Constant.MAX_SIZE ?
         new Result<>(ResultEnum.SUCCESS, Collections.emptyList()):
         new Result<>(ResultEnum.SUCCESS, articleService.listByIds(ids));
    }

}

