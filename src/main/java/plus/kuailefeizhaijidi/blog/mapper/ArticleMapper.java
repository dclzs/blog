package plus.kuailefeizhaijidi.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import plus.kuailefeizhaijidi.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
     * 可根据分类ID查询某一页的文章
     * @param page 分页
     * @param categoryId 分类ID
     * @return 当前页数据
     */
    IPage<Article> selectArticlePage(Page<Article> page, @Param("categoryId") Long categoryId);

}
