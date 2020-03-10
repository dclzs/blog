package plus.kuailefeizhaijidi.blog.entity;

import plus.kuailefeizhaijidi.blog.entity.BaseEntity;
import plus.kuailefeizhaijidi.blog.enums.ResultEnum;

/**
 * <p>
 * 数据响应类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
public class Result<T> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultEnum resultEnum, T data) {
        this(resultEnum.getCode(), resultEnum.getMsg(), data);
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
