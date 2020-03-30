package plus.kuailefeizhaijidi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.kuailefeizhaijidi.blog.entity.User;
import plus.kuailefeizhaijidi.blog.entity.param.LoginParam;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
public interface IUserService extends IService<User> {

    User add(User user);

    User login(LoginParam loginParam);
}
