package plus.kuailefeizhaijidi.blog.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.controller.BaseController;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.IArticleService;

import javax.validation.Valid;

/**
 * <p>
 * 个人文章接口
 * </p>
 *
 * @author dl
 * @since 2020年3月24日
 */
@Api(tags = "用户文章接口")
@RestController
@RequestMapping("user/article")
public class UserArticleController extends BaseController {


    private final IArticleService articleService;

    public UserArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据"),
            @ApiImplicitParam(name = "publicStatus", value = "查询条件 1:发布,0:未发布")})
    @ApiOperation("获取文章")
    @GetMapping
    public Result<IPage<Article>> articleList(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                      @RequestParam(value = "publicStatus", required = false) Integer publicStatus) {
        LambdaQueryWrapper<Article> wrapper = select().eq(Article::getUserId, getUserId());
        if(publicStatus == Constant.ENABLE || publicStatus == Constant.DISABLE){
            wrapper.eq(Article::getPublicStatus, publicStatus);
        }
        return Result.success(articleService.page(new Page<>(current, size), wrapper));
    }


    @ApiOperation("添加文章")
    @PostMapping
    public Result<Article> save(@Valid Article article) {
        Claims claims = getClaims();
        article.setUserId(Long.valueOf(claims.getId()));
        if(StringUtils.isEmpty(article.getArticleAuthor())){
            article.setArticleAuthor(claims.getSubject());
        }
        if(articleService.save(article)) {
            return Result.success(articleService.getById(article.getArticleId()));
        }
        return Result.fault();
    }


    @ApiOperation("修改文章")
    @PutMapping("{articleId}")
    public Result<Article> update(Article article,
                                  @PathVariable String articleId){
        article.setPublicStatus(null);
        article.setCreateTime(null);
        boolean update = articleService.update(article, update(articleId));
        if(update){
            return Result.success(articleService.getById(article.getArticleId()));
        }
        return Result.fault();
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true),
            @ApiImplicitParam(name = "publicStatus", value = "1:发布,0:撤回")})
    @ApiOperation("撤回与发布文章")
    @PutMapping("{articleId}/publish/{publicStatus}")
    public Result publish(@PathVariable String articleId, @PathVariable Integer publicStatus){
        if(publicStatus == Constant.ENABLE || publicStatus == Constant.DISABLE){
            LambdaUpdateWrapper<Article> updateWrapper = update(articleId);
            Article article = new Article();
            article.setPublicStatus(publicStatus);
            boolean update = articleService.update(article, updateWrapper);
            if(update){
                return Result.success(articleService.getById(article.getArticleId()));
            }
            return Result.fault();
        }
        return Result.custom(ResultEnum.PARAM_ERROR);
    }

    private LambdaUpdateWrapper<Article> update(@PathVariable String articleId) {
        return Wrappers.<Article>lambdaUpdate().eq(Article::getUserId, getUserId()).eq(Article::getArticleId, articleId);
    }


    private LambdaQueryWrapper<Article> select() {
        return Wrappers.<Article>lambdaQuery()
                .select(Article::getArticleId,
                        Article::getArticleTitle,
                        Article::getArticleDesc,
                        Article::getArticleAuthor,
                        Article::getCreateTime,
                        Article::getUpdateTime);
    }
}
