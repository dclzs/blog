package plus.kuailefeizhaijidi.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.kuailefeizhaijidi.blog.entity.Article;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
public interface IArticleService extends IService<Article> {

    /**
     * <p>
     * 查询 : 根据 categoryId 状态查询用户列表，分页显示
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页
     * @param categoryId 分类ID
     * @return 分页对象
     */
    IPage<Article> getArticlePage(Page<Article> page, Long categoryId);
}
