package plus.kuailefeizhaijidi.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import plus.kuailefeizhaijidi.blog.entity.Result;
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

    protected String host = "http://127.0.0.1:8080";
    protected final MediaType APPLICATION_FORM_URLENCODED = MediaType.APPLICATION_FORM_URLENCODED;
    protected final MediaType MULTIPART_FORM_DATA =  MediaType.MULTIPART_FORM_DATA;

    protected StringBuffer generatorUrl(String uri) {
        return new StringBuffer(host).append(uri);
    }

    protected String getRequestUrl(String... params) {
        StringBuffer url = generatorUrl(RequestUtils.getRequestURI().replace(RequestUtils.getRequest().getContextPath(), ""));
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

    protected Map<String,Object> newHashMap(Object... objects){
        if (objects.length % 2 == 0) {
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

}
