package plus.kuailefeizhaijidi.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import plus.kuailefeizhaijidi.blog.common.CodeConstant;
import plus.kuailefeizhaijidi.blog.common.MsgConstant;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.exception.AuthorizeException;
import plus.kuailefeizhaijidi.blog.exception.ParamException;

import java.util.Objects;
import java.util.UUID;

/**
 * @author dl
 * @since 2020年03月21日
 */
@ControllerAdvice
public class MyExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler({BindException.class, ParamException.class, MethodArgumentNotValidException.class})
    public Result paramException(Exception e){
        if(e instanceof BindException){
            BindException e1 = (BindException) e;
            log.error("==> BindException:  {} ", e1.getMessage());
            FieldError fieldError = e1.getBindingResult().getFieldError();
            return new Result(CodeConstant.PARAM_ERROR, Objects.requireNonNull(fieldError).getField() + fieldError.getDefaultMessage());
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException e1 = (MethodArgumentNotValidException) e;
            log.error("==> MethodArgumentNotValidException:  {} ", e1.getMessage());
            FieldError fieldError = e1.getBindingResult().getFieldError();
            return new Result(CodeConstant.PARAM_ERROR, Objects.requireNonNull(fieldError).getField() + fieldError.getDefaultMessage());
        }else{
            log.error("==> ParamException:  {} ", e.getMessage());
            return new Result(CodeConstant.PARAM_ERROR, e.getMessage());
        }
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error("==> HttpRequestMethodNotSupportedException: {} ", e.getMessage());
        return new Result(CodeConstant.FAULT, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MultipartException.class)
    public Result handleError1(MultipartException e) {
        log.error("==> MultipartException: {} ", e.getMessage());
        return new Result(CodeConstant.FAULT, MsgConstant.UPLOAD_FILE_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(AuthorizeException.class)
    public Result authorizeException(AuthorizeException e){
        log.error("==> AuthorizeException:  {} ", e.getMessage());
        return new Result(CodeConstant.AUTHORIZE_ERROR, e.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        String uuid = getUuid();
        log.error("==> BlogException:  UUID:{} {} ", uuid, e.getMessage(), e);
        return Result.fault(uuid);
    }

    private String getUuid() {
        String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return "E" + s;
    }

}
