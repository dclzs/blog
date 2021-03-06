package plus.kuailefeizhaijidi.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.entity.vo.BookmarkVo;

/**
 * <p>
 * 书签表 服务类
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
public interface IBookmarkService extends IService<Bookmark> {

    IPage<BookmarkVo> listVo(Long userId, Integer current, Integer size);

    BookmarkVo getVo(Long bookmarkId);
}
