package plus.kuailefeizhaijidi.blog.service.impl;

import plus.kuailefeizhaijidi.blog.entity.Category;
import plus.kuailefeizhaijidi.blog.mapper.CategoryMapper;
import plus.kuailefeizhaijidi.blog.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
