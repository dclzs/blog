package plus.kuailefeizhaijidi.blog.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plus.kuailefeizhaijidi.blog.entity.Article;

import java.util.List;

class IArticleServiceTest extends BaseTest {

    @Test
    public void testSelectList(){
        List<Article> list = articleService.list();
        log.info(">>> list:{} <<<", list);
    }
}