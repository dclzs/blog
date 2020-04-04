package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.entity.Resource;
import plus.kuailefeizhaijidi.blog.mapper.ResourceMapper;
import plus.kuailefeizhaijidi.blog.service.IResourceService;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author dl
 * @since 2020-04-04
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {


    @Override
    public boolean isExists(String hash) {
        LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery().eq(Resource::getResourceHash, hash);
        return getOne(wrapper) != null;
    }
}
