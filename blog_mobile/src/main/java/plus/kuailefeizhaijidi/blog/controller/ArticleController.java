package plus.kuailefeizhaijidi.blog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;

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

    private final RestTemplate restTemplate;

    public ArticleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ApiOperation("根据文章ID查询")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    @GetMapping("{articleId}")
    public Result articleByArticleId(@PathVariable("articleId") Long articleId) {
        Result result = restTemplate.getForObject(getRequestUrl(), Result.class);
        return new Result<>(ResultEnum.SUCCESS, getData(result));
    }

    @ApiOperation("根据文章分类ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true),
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据")})
    @GetMapping("{categoryId}/category")
    public Result articleListByCategoryId(@PathVariable("categoryId") Long categoryId,
                                          @RequestParam(value = "current", defaultValue = "1") Integer current,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Result result = restTemplate.getForObject(getRequestUrl("current", "size"), Result.class, newHashMap("current", current, "size", size));
        return new Result<>(ResultEnum.SUCCESS, getData(result));
    }

    @ApiOperation("文章分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第n页"),
            @ApiImplicitParam(name = "size", value = "有n条数据")})
    @GetMapping
    public Result articleListByCategoryId(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Result result = restTemplate.getForObject(getRequestUrl("current", "size"), Result.class, newHashMap("current", current, "size", size));
        return new Result<>(ResultEnum.SUCCESS, getData(result));
    }

    @ApiOperation("文章ID查询")
    @ApiImplicitParam(name = "ids", value = "文章ID数组")
    @PostMapping
    public Result articleListByIds(@RequestParam("ids") List<String> ids){
        if(ids.size() > Constant.MAX_SIZE || ids.isEmpty()) {
            return new Result<>(ResultEnum.SUCCESS, Collections.emptyList());
        }
        Result result = restTemplate.postForObject(getRequestUrl(), ids, Result.class);
        return new Result<>(ResultEnum.SUCCESS, getData(result));
    }

}

