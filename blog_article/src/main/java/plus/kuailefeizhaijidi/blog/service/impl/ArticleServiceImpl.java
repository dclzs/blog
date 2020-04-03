package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.dto.ArticleDto;
import plus.kuailefeizhaijidi.blog.entity.param.PageParam;
import plus.kuailefeizhaijidi.blog.entity.vo.ArticleVo;
import plus.kuailefeizhaijidi.blog.exception.ParamException;
import plus.kuailefeizhaijidi.blog.mapper.ArticleMapper;
import plus.kuailefeizhaijidi.blog.service.IArticleService;
import plus.kuailefeizhaijidi.blog.service.ICategoryService;

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

    private final Mapper mapper;
    private final ICategoryService categoryService;

    public ArticleServiceImpl(Mapper mapper, ICategoryService categoryService) {
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    @Override
    public ArticleVo save(Long userId, ArticleDto dto) {

        if (!categoryService.isExists(userId, dto.getCategoryId())) {
            throw new ParamException("categoryId 错误");
        }

        Article article = mapper.map(dto, Article.class);
        article.setUserId(userId);

        return save(article) ? baseMapper.selectVo(article.getArticleId()) : null;
    }

    @Override
    public ArticleVo update(Long userId, Long articleId, ArticleDto dto) {
        if(dto.getCategoryId() != null) {
            if (!categoryService.isExists(userId, dto.getCategoryId())) {
                throw new ParamException("categoryId 错误");
            }
        }

        LambdaUpdateWrapper<Article> updateWrapper = Wrappers.<Article>lambdaUpdate()
                .eq(Article::getUserId, userId)
                .eq(Article::getArticleId, articleId);
        Article article = mapper.map(dto, Article.class);
        return update(article, updateWrapper) ? baseMapper.selectVo(articleId) : null;
    }

    @Override
    public boolean updateStatus(Long userId, Long articleId, Integer publicStatus) {
        LambdaUpdateWrapper<Article> wrapper = Wrappers.<Article>lambdaUpdate()
                .set(Article::getPublicStatus,publicStatus)
                .eq(Article::getArticleId, articleId)
                .eq(Article::getUserId, userId);

        return update(wrapper);
    }

    @Override
    public IPage<ArticleVo> pageArticleVo(PageParam param, Long userId) {
        Page<ArticleVo> page = new Page<>(param.getCurrent(), param.getSize());
        return baseMapper.pageVo(page, param, userId);
    }

}
