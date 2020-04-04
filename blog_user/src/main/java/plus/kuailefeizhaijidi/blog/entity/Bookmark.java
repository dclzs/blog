package plus.kuailefeizhaijidi.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 书签表
 * </p>
 *
 * @author dl
 * @since 2020-03-21
 */
@ApiModel(value="Bookmark对象", description="书签表")
public class Bookmark extends BaseEntity<Bookmark> {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "书签ID")
    private Long bookmarkId;

    @NotNull
    @ApiModelProperty(value = "文章ID")
    private Long articleId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    public Long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
        "bookmarkId=" + bookmarkId +
        ", articleId=" + articleId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
