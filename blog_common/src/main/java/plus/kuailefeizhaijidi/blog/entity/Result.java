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
    private String token;
    private String uuid;
    private T data;

    public Result() {
    }

    public Result(String token) {
        this.token = token;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = token;
    }

    public Result(int code, String msg, String token, T data) {
        this.code = code;
        this.msg = msg;
        this.token = token;
        this.data = data;
    }

    public Result(ResultEnum resultEnum, T data) {
        this(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public Result(ResultEnum resultEnum, String token, T data) {
        this(resultEnum.getCode(), resultEnum.getMsg(), token, data);
    }

    public Result(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
    }

    public Result(ResultEnum resultEnum,String token) {
        this(resultEnum.getCode(), resultEnum.getMsg(), token);
    }

    public static <T> Result<T> success(String token, T t) {
        return new Result<>(ResultEnum.OK, token, t);
    }

    public static <T> Result<T> success(T t){
        return new Result<>(ResultEnum.OK, t);
    }

    public static <T> Result<T> token(String token){
        return new Result<>(ResultEnum.OK, token);
    }

    public static <T> Result<T> success(){
        return new Result<>(ResultEnum.OK);
    }

    public static <T> Result<T> fault(){
        return new Result<>(ResultEnum.FAULT);
    }

    public static <T> Result<T> fault(String uuid){
        Result<T> result = new Result<>(ResultEnum.FAULT);
        result.setUuid(uuid);
        return result;
    }

    public static <T> Result<T> custom(ResultEnum resultEnum,String uuid){
        Result<T> result = new Result<>(resultEnum);
        result.setUuid(uuid);
        return result;
    }

    public static <T> Result<T> custom(ResultEnum resultEnum){
        return new Result<>(resultEnum);
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }
}
