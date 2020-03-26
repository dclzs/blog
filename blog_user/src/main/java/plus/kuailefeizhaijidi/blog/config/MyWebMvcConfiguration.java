package plus.kuailefeizhaijidi.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author dl
 * @since 2020年03月21日
 */
@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurationSupport {

    private final MyJwtFilter myJwtFilter;
    private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private final StringHttpMessageConverter stringHttpMessageConverter;


    public MyWebMvcConfiguration(MyJwtFilter myJwtFilter,
                                 MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
                                 StringHttpMessageConverter stringHttpMessageConverter) {
        this.myJwtFilter = myJwtFilter;
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myJwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }


    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,mappingJackson2HttpMessageConverter);
        converters.add(0,stringHttpMessageConverter);
    }
}
