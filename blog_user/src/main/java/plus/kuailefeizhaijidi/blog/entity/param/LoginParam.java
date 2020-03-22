package plus.kuailefeizhaijidi.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author dl
 * @since 2020年03月21日
 */
@ApiModel("登录参数")
public class LoginParam {

    @NotNull(message = "邮箱不能为空！")
    @Email(message = "请输入正确的邮箱！")
    @ApiModelProperty("邮箱")
    private String email;

    @Size(min = 6, message = "请输入正确的密码！")
    @NotNull(message = "密码不能为空！")
    @ApiModelProperty("密码")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
