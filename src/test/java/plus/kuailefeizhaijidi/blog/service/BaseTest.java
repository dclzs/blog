package plus.kuailefeizhaijidi.blog.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.kuailefeizhaijidi.blog.BlogApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class BaseTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected IArticleService articleService;

    @Autowired
    protected ICategoryService categoryService;

}
