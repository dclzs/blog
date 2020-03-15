package plus.kuailefeizhaijidi.blog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;

/**
 * <p>
 * 数据响应类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultEnum resultEnum, T data) {
        this(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public Result(ResultEnum resultEnum) {
        this(resultEnum,null);
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
