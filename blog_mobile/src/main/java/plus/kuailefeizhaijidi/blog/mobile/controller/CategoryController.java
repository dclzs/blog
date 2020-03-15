package plus.kuailefeizhaijidi.blog.mobile.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import plus.kuailefeizhaijidi.blog.controller.BaseController;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;

import java.util.Objects;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@Api(tags = "文章分类相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{

    private final RestTemplate restTemplate;

    public CategoryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @ApiOperation("分类列表")
    public Result categoryList(){
        Result result = Objects.requireNonNull(restTemplate.getForObject(getRequestUrl(), Result.class));
        return new Result<>(ResultEnum.SUCCESS, getData(result));
    }
}

