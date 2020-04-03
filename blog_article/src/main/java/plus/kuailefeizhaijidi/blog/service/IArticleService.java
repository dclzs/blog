package plus.kuailefeizhaijidi.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.dto.ArticleDto;
import plus.kuailefeizhaijidi.blog.entity.param.PageParam;
import plus.kuailefeizhaijidi.blog.entity.vo.ArticleVo;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
public interface IArticleService extends IService<Article> {

    ArticleVo save(Long userId, ArticleDto param);

    ArticleVo update(Long valueOf, Long articleId, ArticleDto dto);

    boolean updateStatus(Long userId, Long articleId, Integer publicStatus);

    IPage<ArticleVo> pageArticleVo(PageParam param, Long userId);
}
