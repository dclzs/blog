package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/article")
public class ArticleController extends BaseController {

    final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("{articleId}")
    public Result<Article> getByArticleId(@PathVariable("articleId") Long articleId) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getById(articleId));
    }

    @GetMapping("{categoryId}")
    public Result<Article> getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return new Result<>(ResultEnum.SUCCESS, articleService.getOne(new QueryWrapper<Article>().eq("category_id", categoryId)));
    }

}

