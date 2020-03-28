package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.mapper.ArticleMapper;
import plus.kuailefeizhaijidi.blog.service.IArticleService;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
