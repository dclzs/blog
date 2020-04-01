package plus.kuailefeizhaijidi.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.common.MsgConstant;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.exception.AuthorizeException;
import plus.kuailefeizhaijidi.blog.util.JwtUtil;
import plus.kuailefeizhaijidi.blog.util.RequestUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dl
 * @since 2020年3月14日
 */
public class BaseController {

    @Resource
    private JwtUtil jwtUtil;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${my.article.host}")
    protected String host;

    protected static final ModelAndView ERROR_404 = new ModelAndView("error/404");

    protected StringBuffer generatorUrl(String uri) {
        return new StringBuffer(host).append(uri);
    }

    protected String getRequestUrl(String... params) {
        StringBuffer url = generatorUrl(RequestUtils.getRequestUri().replace(RequestUtils.getRequest().getContextPath(), ""));
        if (params != null && params.length != 0) {
            url.append("?");
            for (int i = 0; i < params.length; i++) {
                url.append(params[i])
                   .append("={")
                   .append(params[i])
                   .append("}");
                if (i < params.length - 1) {
                    url.append("&");
                }
            }
        }
        log.info("==> requestUrl:{} ", url);
        return url.toString();
    }

    protected Object getData(Result result) {
        if (result == null) {
            return null;
        }
        log.info("==> result:{} =", JSON.toJSONString(result,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        return result.getData();
    }

    protected String getJson(Result result){
        return JSON.toJSONString(getData(result));
    }

    protected Map<String,Object> newHashMap(Object... objects){
        if (objects.length % Constant.TWO == 0) {
            Map<String, Object> hashMap = new HashMap<>(16);
            for (int i = 0; i < objects.length; i++, i++) {
                hashMap.put((String) objects[i], objects[i + 1]);
            }
            return hashMap;
        }
        return Collections.emptyMap();
    }

    protected Object post(){
        return null;
    }

    protected Map<String,Object> newHashMap(Object object){
        return JSON.parseObject(JSON.toJSONString(object));
    }

    protected String getToken(Long id, String subject) {
        return jwtUtil.createJWT(String.valueOf(id), subject, Constant.ROLE_USER);
    }

    protected Long getUserId() throws AuthorizeException {
        return Long.valueOf(getClaims().getId());
    }

    protected String getUserName() throws AuthorizeException{
        return getClaims().getSubject();
    }

    protected Claims getClaims(){
        String authorization = RequestUtils.getHeader(Constant.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(Constant.BEARER_)) {
            throw new AuthorizeException(MsgConstant.NOT_LOGIN);
        }
        String token = authorization.replace(Constant.BEARER_, "");
        try {
            return jwtUtil.parseJWT(token);
        } catch (ExpiredJwtException e) {
            log.error("==> authorize => ExpiredJwtException: {}", e.getMessage());
            throw new AuthorizeException(MsgConstant.LOGIN_EXPIRED);
        } catch (SignatureException e){
            log.error("==> authorize => SignatureException: {}", e.getMessage());
            throw new AuthorizeException(MsgConstant.INVALID_TOKEN);
        }catch (Exception e) {
            log.error("==> authorize: {}", e.getMessage());
            throw new AuthorizeException(MsgConstant.FAULT);
        }
    }
}
