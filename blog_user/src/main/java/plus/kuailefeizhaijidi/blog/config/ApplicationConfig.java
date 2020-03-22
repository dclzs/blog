package plus.kuailefeizhaijidi.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author dl
 * @since 2020年03月21日
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    private final JwtFilter jwtFilter;

    public ApplicationConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }
}
