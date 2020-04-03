package plus.kuailefeizhaijidi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.kuailefeizhaijidi.blog.entity.Category;
import plus.kuailefeizhaijidi.blog.entity.dto.CategoryDto;
import plus.kuailefeizhaijidi.blog.entity.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryVo> listVo(Long userId, Integer displayStatus);

    CategoryVo save(Long userId, CategoryDto dto);

    CategoryVo update(Long userId, Long categoryId, CategoryDto dto);

    boolean updateStatus(Long userId, Long categoryId, Integer displayStatus);

    boolean isExists(Long userId, Long categoryId);

    CategoryVo getVo(Long categoryId);

}
