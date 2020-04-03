package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.entity.vo.BookmarkVo;
import plus.kuailefeizhaijidi.blog.service.IBookmarkService;

/**
 * <p>
 * 书签表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
@Api(tags = "书签接口")
@RestController
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

    private final IBookmarkService bookmarkService;


    public BookmarkController(IBookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @ApiOperation("获取个人书签")
    @GetMapping
    public Result<IPage<BookmarkVo>> bookmarks(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return Result.success(bookmarkService.listVo(getUserId(), current, size));
    }

    @ApiOperation("添加书签")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    @PostMapping
    public Result<BookmarkVo> save(Long articleId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setArticleId(articleId);
        bookmark.setUserId(getUserId());
        if(bookmarkService.save(bookmark)) {
            return Result.success(bookmarkService.getVo(bookmark.getBookmarkId()));
        }
        return Result.fault();
    }

    @ApiOperation("删除书签")
    @DeleteMapping("{bookmarkId}")
    public Result<BookmarkVo> del(@PathVariable Long bookmarkId) {
        LambdaUpdateWrapper<Bookmark> updateWrapper = Wrappers.<Bookmark>lambdaUpdate()
                                                              .eq(Bookmark::getUserId, getUserId())
                                                              .eq(Bookmark::getBookmarkId, bookmarkId)
                                                              .set(Bookmark::getUserId, -bookmarkId);
        if (bookmarkService.update(updateWrapper)) {
            return Result.success(bookmarkService.getVo(bookmarkId));
        }
        return Result.fault();
    }

}

