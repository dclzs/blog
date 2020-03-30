package plus.kuailefeizhaijidi.blog.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import plus.kuailefeizhaijidi.blog.common.Constant;

import java.time.LocalDateTime;

/**
 * <p>
 * Mybatis plus 实体自动填充类
 * </p>
 *
 * @author dl
 * @since 2020-03-11
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "publicStatus", Integer.class, Constant.ENABLE);
        this.strictInsertFill(metaObject, "displayStatus", Integer.class, Constant.ENABLE);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
