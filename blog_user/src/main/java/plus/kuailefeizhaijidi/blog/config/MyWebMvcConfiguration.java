package plus.kuailefeizhaijidi.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author dl
 * @since 2020年03月21日
 */
@Configuration
@EnableSwagger2
public class MyWebMvcConfiguration extends WebMvcConfigurationSupport {

    private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private final StringHttpMessageConverter stringHttpMessageConverter;


    public MyWebMvcConfiguration(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
                                 StringHttpMessageConverter stringHttpMessageConverter) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("plus.kuailefeizhaijidi.blog.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("快乐肥宅基地")
                        .description("快乐肥宅基地，详细信息......就是一群肥宅！")
                        .version("1.0")
//                        .contact(new Contact("啊啊啊啊","blog.csdn.net","aaa@gmail.com"))
//                        .license("The Apache License")
//                        .licenseUrl("http://www.baidu.com")
                        .build());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {}

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,mappingJackson2HttpMessageConverter);
        converters.add(0,stringHttpMessageConverter);
    }
}
