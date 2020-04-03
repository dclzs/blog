package plus.kuailefeizhaijidi.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Result;

/**
 * @author dl
 * @since 2020年4月2日
 */
@Component
public class RemoteCallUtils {

    private static final Logger log = LoggerFactory.getLogger(RemoteCallUtils.class);

    private static String host;

    @Value("${my.article.host}")
    public void setHost(String host) {
        RemoteCallUtils.host = host;
    }

    public static StringBuffer generatorUrl(String uri) {
        return new StringBuffer(host).append(uri);
    }

    public static String getRequestUrl(String... params) {
        return getRequestUrl(RequestUtils.getRequestUri().replace(RequestUtils.getRequest().getContextPath(), ""), params);
    }

    public static String getRequestUrl(String uri) {
        if(!uri.startsWith(Constant.BACKSLASH)){
            uri = Constant.BACKSLASH + uri;
        }
        return getRequestUrl(uri, null);
    }

    public static String getRequestUrl(String uri, String[] params) {
        StringBuffer url = generatorUrl(uri);
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

    public static Object getData(Result result) {
        if (result == null) {
            return null;
        }
        log.info("==> result:{} =", JSON.toJSONString(result,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        return result.getData();
    }

    public static String getJson(Result result){
        return JSON.toJSONString(getData(result));
    }
}
