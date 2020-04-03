package plus.kuailefeizhaijidi.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import plus.kuailefeizhaijidi.blog.entity.Article;
import plus.kuailefeizhaijidi.blog.entity.param.PageParam;
import plus.kuailefeizhaijidi.blog.entity.vo.ArticleVo;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 查询分页
     * @param page 分页对象
     * @param param 查询条件
     * @param userId 用户ID
     * @return 文章分页数据
     */
    IPage<ArticleVo> pageVo(Page<ArticleVo> page, @Param("w") PageParam param, @Param("userId") Long userId);

    /**
     * 查询文章
     * @param articleId 文章ID
     * @return 文章数据
     */
    ArticleVo selectVo(@Param("articleId") Long articleId);
}
