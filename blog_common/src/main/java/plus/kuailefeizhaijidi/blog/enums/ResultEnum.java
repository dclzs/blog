package plus.kuailefeizhaijidi.blog.enums;

import org.springframework.http.HttpStatus;

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
    SUCCESS(HttpStatus.OK.value(),"SUCCESS"),
    /**
     * 请求失败
     */
    FAULT(HttpStatus.INTERNAL_SERVER_ERROR.value(),"FAULT"),
    /**
     * 参数错误
     */
    PARAM_ERROR(HttpStatus.BAD_REQUEST.value(), "PARAM ERROR"),
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
