package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.entity.Category;
import plus.kuailefeizhaijidi.blog.entity.dto.CategoryDto;
import plus.kuailefeizhaijidi.blog.entity.vo.CategoryVo;
import plus.kuailefeizhaijidi.blog.mapper.CategoryMapper;
import plus.kuailefeizhaijidi.blog.service.ICategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    private final Mapper mapper;

    public CategoryServiceImpl(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<CategoryVo> listVo(Long userId, Integer displayStatus) {
        LambdaQueryWrapper<Category> wrapper = Wrappers.<Category>lambdaQuery().eq(Category::getUserId, userId);
        if (displayStatus != null) {
            wrapper.eq(Category::getDisplayStatus, displayStatus);
        }
        List<CategoryVo> categoryVos = list(wrapper)
                .stream()
                .map(t -> mapper.map(t, CategoryVo.class))
                .collect(Collectors.toList());
        return categoryVos;
    }

    @Override
    public CategoryVo save(Long userId, CategoryDto dto) {
        Category category = mapper.map(dto, Category.class);
        category.setUserId(userId);
        if(save(category)){
            category = getById(category.getCategoryId());
            return mapper.map(category, CategoryVo.class);
        }
        return null;
    }

    @Override
    public CategoryVo update(Long userId, Long categoryId, CategoryDto dto) {
        LambdaUpdateWrapper<Category> wrapper = Wrappers.<Category>lambdaUpdate()
                .eq(Category::getUserId, userId)
                .eq(Category::getCategoryId, categoryId);

        Category category = mapper.map(dto, Category.class);
        if(update(category, wrapper)){
            category = getById(categoryId);
            return mapper.map(category, CategoryVo.class);
        }
        return null;
    }

    @Override
    public boolean updateStatus(Long userId, Long categoryId, Integer displayStatus) {
        LambdaUpdateWrapper<Category> wrapper = Wrappers.<Category>lambdaUpdate()
                .set(Category::getDisplayStatus, displayStatus)
                .eq(Category::getUserId, userId)
                .eq(Category::getCategoryId, categoryId);
        return update(wrapper);
    }

    @Override
    public boolean isExists(Long userId, Long categoryId) {
        LambdaQueryWrapper<Category> wrapper = Wrappers.<Category>lambdaQuery()
                .eq(Category::getCategoryId, categoryId)
                .eq(Category::getUserId, userId);
        return getOne(wrapper) != null;
    }
}
