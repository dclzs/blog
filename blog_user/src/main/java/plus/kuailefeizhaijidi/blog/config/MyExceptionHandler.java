package plus.kuailefeizhaijidi.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import plus.kuailefeizhaijidi.blog.entity.Result;

import java.util.Objects;

/**
 * @author dl
 * @since 2020年03月21日
 */
@ControllerAdvice
public class MyExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Result paramException(BindException e){
        log.info("==> BindException:  {} ", e.getAllErrors());
        return new Result(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.info("==> exception:  {} ", e.getMessage(), e);
        return new Result(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


}
