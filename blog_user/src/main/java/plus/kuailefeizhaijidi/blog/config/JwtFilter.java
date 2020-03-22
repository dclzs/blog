package plus.kuailefeizhaijidi.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import plus.kuailefeizhaijidi.blog.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dl
 * @since 2020年03月21日
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(getClass());

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("过拦截器了...");
        return true;
    }
}
