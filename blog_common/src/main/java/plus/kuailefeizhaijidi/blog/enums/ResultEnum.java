package plus.kuailefeizhaijidi.blog.enums;

import plus.kuailefeizhaijidi.blog.common.CodeConstant;
import plus.kuailefeizhaijidi.blog.common.MsgConstant;

/**
 * <p>
 * 数据响应枚举
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
public enum ResultEnum implements BaseEnum{
    /**
     * 请求成功
     */
    OK(CodeConstant.OK, MsgConstant.OK),
    /**
     * 请求失败
     */
    FAULT(CodeConstant.FAULT,MsgConstant.FAULT),
    /**
     * 参数错误
     */
    PARAM_ERROR(CodeConstant.PARAM_ERROR, MsgConstant.PARAM_ERROR),
    /**
     * 账号或密码错误
     */
    ACC_PWD_ERROR(CodeConstant.PARAM_ERROR, MsgConstant.ACC_PWD_ERROR),
    /**
     * 授权错误
     */
    AUTHORIZE_ERROR(CodeConstant.AUTHORIZE_ERROR, MsgConstant.AUTHORIZE_ERROR),
    ;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
