package plus.kuailefeizhaijidi.blog.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.controller.BaseController;
import plus.kuailefeizhaijidi.blog.entity.Category;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.ICategoryService;

import java.util.List;

/**
 * <p>
 *     个人分类接口
 * </p>
 * @author dl
 * @since 2020年3月24日
 */
@Api(tags = "用户文章分类接口")
@RestController
@RequestMapping("user/category")
public class UserCategoryController extends BaseController {


    private final ICategoryService categoryService;

    public UserCategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "displayStatus", value = "查询条件 1:显示,0:不显示")})
    @ApiOperation("个人分类列表")
    @GetMapping
    public Result<List<Category>> categoryList(@RequestParam(value = "displayStatus", required = false) Integer displayStatus){
        LambdaQueryWrapper<Category> wrapper = Wrappers.<Category>lambdaQuery().eq(Category::getUserId, getUserId());
        if (displayStatus != null) {
            wrapper.eq(Category::getDisplayStatus, displayStatus);
        }
        return new Result<>(ResultEnum.SUCCESS, categoryService.list(wrapper));
    }

    @ApiOperation("添加分类")
    @PostMapping
    public Result<Category> save(Category category) {
        category.setUserId(getUserId());
        if(categoryService.save(category)){
            return Result.success(categoryService.getById(category.getCategoryId()));
        }
        return Result.fault();
    }


    @ApiOperation("修改分类")
    @PutMapping("{categoryId}")
    public Result<Category> update(Category category, @PathVariable String categoryId) {
        LambdaUpdateWrapper<Category> updateWrapper = update(categoryId);
        category.setDisplayStatus(null);
        category.setCreateTime(null);
        if(categoryService.update(category, updateWrapper)){
            return Result.success(categoryService.getById(categoryId));
        }
        return Result.fault();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true),
            @ApiImplicitParam(name = "displayStatus", value = "1:显示,0:不显示")})
    @ApiOperation("修改该分类显示状态")
    @PutMapping("{categoryId}/display/{displayStatus}")
    public Result display(@PathVariable String categoryId, @PathVariable Integer displayStatus){
        if(displayStatus == Constant.ENABLE || displayStatus == Constant.DISABLE) {
            LambdaUpdateWrapper<Category> updateWrapper = update(categoryId);
            Category category = new Category();
            category.setDisplayStatus(displayStatus);
            if (categoryService.update(category, updateWrapper)) {
                return Result.success(categoryService.getById(categoryId));
            }
            return Result.fault();
        }
        return Result.custom(ResultEnum.PARAM_ERROR);
    }

    private LambdaUpdateWrapper<Category> update(@PathVariable String categoryId) {
        return Wrappers.<Category>lambdaUpdate().eq(Category::getUserId, getUserId()).eq(Category::getCategoryId, categoryId);
    }
}
