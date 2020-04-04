package plus.kuailefeizhaijidi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.kuailefeizhaijidi.blog.entity.Resource;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author dl
 * @since 2020-04-04
 */
public interface IResourceService extends IService<Resource> {

    boolean isExists(String hash);

}
