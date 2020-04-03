package plus.kuailefeizhaijidi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import plus.kuailefeizhaijidi.blog.common.MsgConstant;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.entity.vo.BookmarkVo;
import plus.kuailefeizhaijidi.blog.exception.BlogException;
import plus.kuailefeizhaijidi.blog.mapper.BookmarkMapper;
import plus.kuailefeizhaijidi.blog.service.IBookmarkService;
import plus.kuailefeizhaijidi.blog.util.RemoteCallUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public BookmarkServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public IPage<BookmarkVo> listVo(Long userId, Integer current, Integer size) {
        LambdaQueryWrapper<Bookmark> queryWrapper = Wrappers.<Bookmark>lambdaQuery().eq(Bookmark::getUserId, userId).orderByDesc(Bookmark::getCreateTime);
        Page<Bookmark> page = page(new Page<>(current, size), queryWrapper);
        // 获取文章
        List<String> ids = page.getRecords().stream().map(t -> String.valueOf(t.getArticleId())).collect(Collectors.toList());
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.addAll("ids", ids);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(param, httpHeaders);
        Result result = restTemplate.postForObject(RemoteCallUtils.getRequestUrl("article"), request, Result.class);
        List<BookmarkVo> bookmarkVOList;
        try {
            bookmarkVOList = Arrays.asList(objectMapper.readValue(RemoteCallUtils.getJson(result), BookmarkVo[].class));
        } catch (JsonProcessingException e) {
            throw new BlogException(MsgConstant.JSON_PARSE_ERROR);
        }
        IPage<BookmarkVo> vo = new Page<>(current, size);
        vo.setPages(page.getPages());
        vo.setTotal(page.getTotal());
        vo.setRecords(BookmarkVo.merge(page.getRecords(), bookmarkVOList));
        return vo;
    }

    @Override
    public BookmarkVo getVo(Long bookmarkId) {
        Bookmark bookmark = getById(bookmarkId);
        Result result = restTemplate.getForObject(RemoteCallUtils.getRequestUrl("article/" + bookmark.getArticleId()), Result.class);
        BookmarkVo bookmarkVO;
        try {
            bookmarkVO = objectMapper.readValue(RemoteCallUtils.getJson(result), BookmarkVo.class);
        } catch (JsonProcessingException e) {
            throw new BlogException(MsgConstant.JSON_PARSE_ERROR);
        }
        bookmarkVO.setCollectTime(bookmark.getCreateTime());
        return bookmarkVO;
    }
}
