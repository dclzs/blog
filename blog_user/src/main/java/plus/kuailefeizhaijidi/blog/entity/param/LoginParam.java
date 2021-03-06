package plus.kuailefeizhaijidi.blog.entity.param;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author dl
 * @since 2020年03月21日
 */
public class LoginParam {

    @NotNull
    @Email
    @ApiModelProperty(value = "邮箱", required = true, dataType = "string")
    private String email;

    @Size(min = 6)
    @NotNull
    @ApiModelProperty(value = "密码", required = true, dataType = "string")
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

    @Override
    public String toString() {
        return "LoginParam{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
