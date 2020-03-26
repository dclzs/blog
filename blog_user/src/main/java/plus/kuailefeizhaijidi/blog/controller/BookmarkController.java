package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.service.IBookmarkService;

import java.util.List;

/**
 * <p>
 * 书签表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
@RestController
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

    private final IBookmarkService bookmarkService;

    public BookmarkController(IBookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @ApiOperation("获取个人书签")
    @GetMapping
    public Result<Page<Bookmark>> bookmarks(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size){
        LambdaQueryWrapper<Bookmark> queryWrapper = Wrappers.<Bookmark>lambdaQuery()
                                                            .eq(Bookmark::getUserId, getUserId())
                                                            .orderByDesc(Bookmark::getCreateTime);
        Page<Bookmark> page = bookmarkService.page(new Page<>(current, size), queryWrapper);
        return Result.success(page);
    }

    @ApiOperation("添加书签")
    @PostMapping
    public Result<Bookmark> save(Bookmark bookmark){
        bookmark.setUserId(getUserId());
        if(bookmarkService.save(bookmark)) {
            return Result.success();
        }
        return Result.fault();
    }

    @ApiOperation("删除书签")
    @DeleteMapping("{bookmarkId}")
    public Result<List<Bookmark>> del(@PathVariable("bookmarkId") Long bookmarkId){
        LambdaUpdateWrapper<Bookmark> updateWrapper = Wrappers.<Bookmark>lambdaUpdate()
                                                              .set(Bookmark::getUserId, -bookmarkId)
                                                              .eq(Bookmark::getBookmarkId, bookmarkId)
                                                              .eq(Bookmark::getUserId, getUserId());
        if (bookmarkService.update(updateWrapper)) {
            return Result.success();
        }
        return Result.fault();
    }

    private Long getUserId(){
        return 11111111111L;
    }
}

