package plus.kuailefeizhaijidi.blog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dl
 * @since 2020年3月14日
 */
public class RequestUtils {

    public static ServletRequestAttributes getRequestAttributes(){
        return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest(){
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    public static String getRequestURI(){
        return getRequest().getRequestURI();
    }
}
