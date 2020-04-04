package plus.kuailefeizhaijidi.blog.controller;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.common.MsgConstant;
import plus.kuailefeizhaijidi.blog.exception.AuthorizeException;
import plus.kuailefeizhaijidi.blog.util.JwtUtils;
import plus.kuailefeizhaijidi.blog.util.RequestUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dl
 * @since 2020年3月14日
 */
public class BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    protected static final ModelAndView ERROR_404 = new ModelAndView("error/404");

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

    protected Map<String,Object> newHashMap(Object object){
        return JSON.parseObject(JSON.toJSONString(object));
    }

    protected String getToken(Long id, String subject) {
        return JwtUtils.me().createJWT(String.valueOf(id), subject, Constant.ROLE_USER);
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
            return JwtUtils.me().parseJWT(token);
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
