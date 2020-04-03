package plus.kuailefeizhaijidi.blog.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.controller.BaseController;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.entity.dto.CategoryDto;
import plus.kuailefeizhaijidi.blog.entity.vo.CategoryVo;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.ICategoryService;

import javax.validation.Valid;
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

    @ApiImplicitParam(name = "displayStatus", value = "查询条件 1:显示,0:不显示")
    @ApiOperation("个人分类列表")
    @GetMapping
    public Result<List<CategoryVo>> categoryList(@RequestParam(value = "displayStatus", required = false) Integer displayStatus){
        return Result.success(categoryService.listVo(getUserId(), displayStatus));
    }

    @ApiOperation(value = "添加分类",notes = "默认非显示状态")
    @PostMapping
    public Result<CategoryVo> save(@Valid @RequestBody CategoryDto dto) {
        CategoryVo vo = categoryService.save(getUserId(), dto);
        return Result.success(vo);
    }

    @ApiOperation("修改分类")
    @PutMapping("{categoryId}")
    public Result<CategoryVo> update(@RequestBody CategoryDto dto, @PathVariable Long categoryId) {
        CategoryVo vo = categoryService.update(getUserId(), categoryId, dto);
        return Result.success(vo);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true),
            @ApiImplicitParam(name = "displayStatus", value = "1:显示,0:不显示", required = true)})
    @ApiOperation("修改该分类显示状态")
    @PutMapping("{categoryId}/display/{displayStatus}")
    public Result<CategoryVo> display(@PathVariable Long categoryId, @PathVariable Integer displayStatus){
        if(displayStatus == Constant.ENABLE || displayStatus == Constant.DISABLE) {
            if(categoryService.updateStatus(getUserId(), categoryId, displayStatus)) {
                return Result.success(categoryService.getVo(categoryId));
            }
        }
        return Result.custom(ResultEnum.PARAM_ERROR);
    }

}
