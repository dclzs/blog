package plus.kuailefeizhaijidi.blog.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dl
 * @since 2020年4月3日
 */
@Configuration
public class DozerConfig {

    @Bean
    public Mapper dozerMapper(){
        return DozerBeanMapperBuilder.create()
                .withMappingBuilder(beanMappingBuilder())
                .build();
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                // 个性化配置添加在此
            }
        };
    }
}
