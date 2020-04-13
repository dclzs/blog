package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dozermapper.core.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.User;
import plus.kuailefeizhaijidi.blog.entity.param.LoginParam;
import plus.kuailefeizhaijidi.blog.entity.vo.UserVo;
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

    private final BCryptPasswordEncoder encoder;
    private final Mapper mapper;

    public UserServiceImpl(BCryptPasswordEncoder encoder, Mapper mapper) {
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    public User add(User user){
        if(getByEmail(user.getEmail()) == null){
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            if(save(user)){
                return getById(user.getUserId());
            }
        }
        return null;
    }

    @Override
    public boolean updatePassword(LoginParam param){
        if(getByEmail(param.getEmail()) == null){
            String password = encoder.encode(param.getPassword());
            LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate()
                    .eq(User::getEmail, param.getEmail())
                    .set(User::getPassword, password);
            return update(updateWrapper);
        }
        return false;
    }

    @Override
    public User login(LoginParam param) {
        User user = getByEmail(param.getEmail());
        if (user != null && user.getStatus() == Constant.ENABLE && encoder.matches(param.getPassword(), user.getPassword())) {
            user.setPassword(null);
            return user;
        }
        return null;
    }

    @Override
    public UserVo getVo(Long userId) {
        return mapper.map(getById(userId), UserVo.class);
    }

    private User getByEmail(String email){
        return getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email));
    }

}
