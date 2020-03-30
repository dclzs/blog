package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.mapper.BookmarkMapper;
import plus.kuailefeizhaijidi.blog.service.IBookmarkService;

/**
 * <p>
 * 书签表 服务实现类
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
@Service
public class BookmarkServiceImpl extends ServiceImpl<BookmarkMapper, Bookmark> implements IBookmarkService {

}
