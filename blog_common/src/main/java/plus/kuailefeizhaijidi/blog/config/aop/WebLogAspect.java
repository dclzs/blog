package plus.kuailefeizhaijidi.blog.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import plus.kuailefeizhaijidi.blog.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author dl
 * @since 2020年4月3日
 */

@Aspect
@Component
@Order(5)
public class WebLogAspect {

    private Logger log = LoggerFactory.getLogger(getClass());
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * plus.kuailefeizhaijidi.blog.controller..*(..))")
    public void webLog() {}

    @Before(value = "webLog()")
    public void before(JoinPoint point) {
        startTime.set(System.currentTimeMillis());

        log.info("<>=======================================================================<>");
        HttpServletRequest request = RequestUtils.getRequest();
        log.info("<> IP: {}", request.getRemoteAddr());
        log.info("<> URL: {}", request.getRequestURL().toString());
        log.info("<> HTTP_METHOD: {}", request.getMethod());
        log.info("<> CLASS_NAME: {}", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        log.info("<> ARGS: {}", Arrays.toString(point.getArgs()));
        log.info("<>=======================================================================<>");
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void afterReturning(Object ret) {
        log.info("<>=======================================================================<>");
        log.info("<> Response: {}", ret);
        log.info("<> Spend Time: {}", (System.currentTimeMillis() - startTime.get()));
        log.info("<>=======================================================================<>");

        startTime.remove();
    }

}
