package plus.kuailefeizhaijidi.blog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.entity.User;
import plus.kuailefeizhaijidi.blog.entity.param.LoginParam;
import plus.kuailefeizhaijidi.blog.service.IUserService;
import plus.kuailefeizhaijidi.blog.util.JwtUtil;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {


    private final IUserService userService;

    private final JwtUtil jwtUtil;

    public UserController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Result<User> login(@Valid LoginParam loginParam){
        User login = userService.login(loginParam);
        if (login != null) {
            String token = jwtUtil.createJWT(String.valueOf(login.getUserId()), login.getNickName(), Constant.ROLE_USER);
            return Result.success(Constant.BEARER_ + token, login);
        }
        return Result.fault();
    }

}

