package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Category;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.ICategoryService;

import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@Api(tags = "文章分类接口")
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ApiOperation("分类列表")
    public Result<List<Category>> categoryList(){
        return new Result<>(ResultEnum.SUCCESS, categoryService.list(Wrappers.<Category>lambdaQuery().eq(Category::getDisplayStatus, Constant.ENABLE)));
    }

}

