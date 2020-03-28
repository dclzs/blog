package plus.kuailefeizhaijidi.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.exception.AuthorizeException;
import plus.kuailefeizhaijidi.blog.exception.ParamException;

import java.util.Objects;

/**
 * @author dl
 * @since 2020年03月21日
 */
@ControllerAdvice
public class MyExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler({BindException.class, ParamException.class})
    public Result paramException(Exception e){
        if(e instanceof BindException){
            BindException e1 = (BindException) e;
            log.error("==> BindException:  {} ", e1.getAllErrors());
            return new Result(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(e1.getFieldError()).getDefaultMessage());
        }else{
            log.error("==> ParamException:  {} ", e.getMessage());
            return new Result(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.error("==> BlogException:  {} ", e.getMessage(), e);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AuthorizeException.class)
    public Result authorizeException(AuthorizeException e){
        log.error("==> AuthorizeException:  {} ", e.getMessage());
        return new Result(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }


}
