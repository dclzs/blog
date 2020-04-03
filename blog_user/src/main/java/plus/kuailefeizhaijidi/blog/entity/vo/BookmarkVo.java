package plus.kuailefeizhaijidi.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import plus.kuailefeizhaijidi.blog.entity.Bookmark;
import plus.kuailefeizhaijidi.blog.exception.BlogException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dl
 * @since 2020年03月28日
 */
@ApiModel("书签")
public class BookmarkVo {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("文章ID")
    private Long articleId;
    @ApiModelProperty("文章标题")
    private String articleTitle;
    @ApiModelProperty("文章描述")
    private String articleDesc;
    @ApiModelProperty("文章作者")
    private String articleAuthor;
    @ApiModelProperty("文章创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("文章更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("收藏时间")
    private LocalDateTime collectTime;

    public static List<BookmarkVo> merge(List<Bookmark> bookmarkList, List<BookmarkVo> bookmarkVOList) {
        if(bookmarkList.size() != bookmarkVOList.size()){
            throw new BlogException("BookmarkVo 合并失败，长度不一样！");
        }
        Map<Long, BookmarkVo> voMap = bookmarkVOList.stream().filter(t -> t != null).collect(Collectors.toMap(BookmarkVo::getArticleId, t -> t));
        bookmarkVOList = new ArrayList<>();
        for (Bookmark bookmark : bookmarkList) {
            BookmarkVo bookmarkVo = voMap.get(bookmark.getArticleId());
            if(bookmarkVo == null){
                bookmarkVo = new BookmarkVo();
            }
            bookmarkVo.setArticleId(bookmark.getArticleId());
            bookmarkVo.setCollectTime(bookmark.getCreateTime());
            bookmarkVOList.add(bookmarkVo);
        }
        return bookmarkVOList;
    }


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(LocalDateTime collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "BookmarkVo{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleDesc='" + articleDesc + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", collectTime=" + collectTime +
                '}';
    }
}
