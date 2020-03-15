package plus.kuailefeizhaijidi.blog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.kuailefeizhaijidi.blog.BlogBaseApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogBaseApplication.class)
public class BaseTest {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected IArticleService articleService;

    @Autowired
    protected ICategoryService categoryService;

    @Test
    public void insert(){
//
//        String[] title = {"越人歌","木兰词·拟古决绝词柬友","江城子·乙卯正月二十日夜记梦","离思五首·其四","南歌子词二首 / 新添声杨柳枝词","卜算子·我住长江头","折桂令·春情","白头吟","上邪","三五七言 / 秋风词"};
//        String[] author = {"佚名","纳兰性德","苏轼","元稹","温庭筠","李之仪","徐再思","卓文君","佚名","李白"};
//        String[] content = {
//                "山有木兮木有枝，心悦君兮君不知。",
//                "人生若只如初见，何事秋风悲画扇。",
//                "十年生死两茫茫，不思量，自难忘。",
//                "曾经沧海难为水，除却巫山不是云。",
//                "玲珑骰子安红豆，入骨相思知不知。",
//                "只愿君心似我心，定不负相思意。",
//                "平生不会相思，才会相思，便害相思。",
//                "愿得一心人，白头不相离。",
//                "山无陵，江水为竭。冬雷震震，夏雨雪。天地合，乃敢与君绝。",
//                "入我相思门，知我相思苦。",};
//
//        List<Article> articleList = new ArrayList<>();
//        List<Category> categoryList = new ArrayList<>();
//
//        Long userId = 11111111111L;
//        Category category = new Category();
//        category.setUserId(userId);
//        category.setCategoryName("诗句");
//        category.setCategoryDesc("这里是诗句描述");
//        categoryService.save(category);
//        Long categoryId = category.getCategoryId();
//        for (int i = 0; i < 10; i++) {
//            Article article = new Article();
//            article.setCategoryId(categoryId);
//            article.setArticleAuthor(author[i]);
//            article.setTags(author[i]);
//            article.setArticleDesc(content[i]);
//            article.setArticleContent(content[i]);
//            article.setArticleTitle(title[i]);
//            article.setUserId(userId);
//            articleList.add(article);
//        }
//        articleService.saveBatch(articleList);
    }
}
