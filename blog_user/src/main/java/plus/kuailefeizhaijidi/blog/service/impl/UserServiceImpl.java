package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.entity.User;
import plus.kuailefeizhaijidi.blog.entity.param.LoginParam;
import plus.kuailefeizhaijidi.blog.mapper.UserMapper;
import plus.kuailefeizhaijidi.blog.service.IUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    final BCryptPasswordEncoder encoder;

    public UserServiceImpl(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User add(User user){
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        if(save(user)){
            return getById(user.getUserId());
        }
        return null;
    }

    @Override
    public User login(LoginParam loginParam) {
        User user = getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, loginParam.getEmail()));
        if (user != null && encoder.matches(loginParam.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

}
