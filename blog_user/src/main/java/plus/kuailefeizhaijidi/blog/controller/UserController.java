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
import plus.kuailefeizhaijidi.blog.entity.vo.UserVo;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;
import plus.kuailefeizhaijidi.blog.service.IUserService;

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

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(@Valid LoginParam param){
        User login = userService.login(param);
        if (login != null) {
            return Result.token(Constant.BEARER_ + getToken(login.getUserId(), login.getNickName()));
        }
        return Result.custom(ResultEnum.ACC_PWD_ERROR);
    }

    @ApiOperation("获取个人信息")
    @PostMapping
    public Result<UserVo> user(){
        UserVo vo = userService.getVo(getUserId());
        return Result.success(vo);
    }

}

