package plus.kuailefeizhaijidi.blog.config.aop;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.util.JwtUtils;
import plus.kuailefeizhaijidi.blog.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

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
    private ThreadLocal<String> logId = new ThreadLocal<>();

    @Pointcut("execution(public * plus.kuailefeizhaijidi.blog.controller..*(..))")
    public void webLog() {}

    @Before(value = "webLog()")
    public void before(JoinPoint point) {
        startTime.set(System.currentTimeMillis());
        logId.set(getUuid());

        log.info("<>=======================================================================<>LOG_ID:{}<>", logId.get());
        HttpServletRequest request = RequestUtils.getRequest();
        String authorization = request.getHeader(Constant.AUTHORIZATION);
        if (authorization != null && authorization.startsWith(Constant.BEARER_)) {
            authorization = authorization.replace(Constant.BEARER_, "");
            Claims claims = JwtUtils.me().parseJWT(authorization);
            log.info("<> USER_ID: {}", claims.getId());
            log.info("<> USERNAME: {}", claims.getSubject());
        }
        log.info("<> IP: {}", request.getRemoteAddr());
        log.info("<> URL: {}", request.getRequestURL().toString());
        log.info("<> HTTP_METHOD: {}", request.getMethod());
        log.info("<> CLASS_NAME: {}", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        log.info("<> ARGS: {}", Arrays.toString(point.getArgs()));
        log.info("<>=======================================================================<>LOG_ID:{}<>", logId.get());
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void afterReturning(Object ret) {
        log.info("<>=======================================================================<>LOG_ID:{}<>", logId.get());
        log.info("<> RESPONSE: {}", ret);
        log.info("<> SPEND_TIME: {}", (System.currentTimeMillis() - startTime.get()));
        log.info("<>=======================================================================<>LOG_ID:{}<>", logId.get());

        logId.remove();
        startTime.remove();
    }


    private String getUuid() {
        String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return "L" + s;
    }
}
