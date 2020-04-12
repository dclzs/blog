package plus.kuailefeizhaijidi.blog.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.controller.BaseController;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.entity.dto.ArticleDto;
import plus.kuailefeizhaijidi.blog.entity.param.PageParam;
import plus.kuailefeizhaijidi.blog.entity.vo.ArticleVo;
import plus.kuailefeizhaijidi.blog.exception.ParamException;
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

    @ApiOperation("获取指定文章信息")
    @GetMapping("{articleId}")
    public Result<ArticleVo> article(@PathVariable Long articleId){
        Long userId = getUserId();
        if(articleService.get(userId, articleId) == null){
            return Result.success();
        }else {
            return Result.success(articleService.getVo(articleId));
        }
    }

    @ApiOperation("获取文章")
    @GetMapping
    public Result<IPage<ArticleVo>> articleList(PageParam param) {
        return Result.success(articleService.pageArticleVo(param, getUserId()));
    }

    @ApiOperation(value = "添加文章")
    @PostMapping
    public Result<ArticleVo> save(@Valid @RequestBody ArticleDto dto) {
        Claims claims = getClaims();
        if (StringUtils.isEmpty(dto.getArticleAuthor())) {
            dto.setArticleAuthor(claims.getSubject());
        }
        ArticleVo vo = articleService.save(Long.valueOf(claims.getId()), dto);
        return Result.success(vo);
    }


    @ApiOperation("修改文章")
    @PutMapping("{articleId}")
    public Result<ArticleVo> update(@RequestBody ArticleDto dto,
                                    @PathVariable Long articleId) {
        Long userId = getUserId();
        if(null == articleService.get(userId, articleId)){
            throw new ParamException("articleId 错误");
        }
        ArticleVo vo = articleService.update(userId, articleId, dto);
        return Result.success(vo);
    }


//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true),
//            @ApiImplicitParam(name = "publicStatus", value = "1:发布,0:撤回", required = true)})
//    @ApiOperation("撤回与发布文章")
//    @PutMapping("{articleId}/publish/{publicStatus}")
//    public Result<ArticleVo> publish(@PathVariable Long articleId, @PathVariable Integer publicStatus) {
//        if (publicStatus == Constant.ENABLE || publicStatus == Constant.DISABLE) {
//            if(articleService.updateStatus(getUserId(), articleId, publicStatus)) {
//                return Result.success(articleService.getVo(articleId));
//            }
//        }
//        return Result.custom(ResultEnum.PARAM_ERROR);
//    }

}
