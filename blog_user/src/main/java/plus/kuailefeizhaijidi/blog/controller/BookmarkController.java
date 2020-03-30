package plus.kuailefeizhaijidi.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.entity.vo.BookmarkVO;
import plus.kuailefeizhaijidi.blog.service.IBookmarkService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public BookmarkController(IBookmarkService bookmarkService, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.bookmarkService = bookmarkService;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @ApiOperation("获取个人书签")
    @GetMapping
    public Result<IPage<BookmarkVO>> bookmarks(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) throws JsonProcessingException {
        LambdaQueryWrapper<Bookmark> queryWrapper = Wrappers.<Bookmark>lambdaQuery().eq(Bookmark::getUserId, getUserId()).orderByDesc(Bookmark::getCreateTime);
        Page<Bookmark> page = bookmarkService.page(new Page<>(current, size), queryWrapper);
        // 获取文章
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        List<String> ids = page.getRecords().stream().map(t -> String.valueOf(t.getArticleId())).collect(Collectors.toList());
        map.addAll("ids", ids);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        Result result = restTemplate.postForObject(getRequestUrl().replace("bookmark","article"), request, Result.class);
        List<BookmarkVO> bookmarkVOList = Arrays.asList(objectMapper.readValue(getJson(result), BookmarkVO[].class));
        IPage<BookmarkVO> voPage = new Page<>(current,size);
        voPage.setPages(page.getPages());
        voPage.setTotal(page.getTotal());
        voPage.setRecords(BookmarkVO.merge(page.getRecords(), bookmarkVOList));
        return Result.success(voPage);
    }

    @ApiOperation("添加书签")
    @PostMapping
    public Result<Bookmark> save(@Valid Bookmark bookmark){
        bookmark.setUserId(getUserId());
        if(bookmarkService.save(bookmark)) {
            return Result.success();
        }
        return Result.fault();
    }

    @ApiOperation("删除书签")
    @DeleteMapping("{bookmarkId}")
    public Result del(@PathVariable("bookmarkId") Long bookmarkId){
        LambdaUpdateWrapper<Bookmark> updateWrapper = Wrappers.<Bookmark>lambdaUpdate()
                                                              .eq(Bookmark::getUserId, getUserId())
                                                              .eq(Bookmark::getBookmarkId, bookmarkId)
                                                              .set(Bookmark::getUserId, -bookmarkId);
        if (bookmarkService.update(updateWrapper)) {
            return Result.success();
        }
        return Result.fault();
    }

}

