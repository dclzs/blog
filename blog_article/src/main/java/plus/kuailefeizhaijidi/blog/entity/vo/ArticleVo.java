package plus.kuailefeizhaijidi.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author dl
 * @since 2020年4月3日
 */
public class ArticleVo {

    @ApiModelProperty(value = "文章ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;
    @ApiModelProperty(value = "分类ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    @ApiModelProperty(value = "用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    @ApiModelProperty(value = "标签（逗号分隔）")
    private String tags;
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    @ApiModelProperty(value = "原作者")
    private String articleAuthor;
    @ApiModelProperty(value = "文章描述")
    private String articleDesc;
    @ApiModelProperty(value = "文章内容")
    private String articleContent;
    @ApiModelProperty(value = "公开状态")
    private Integer publicStatus;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Integer publicStatus) {
        this.publicStatus = publicStatus;
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

    @Override
    public String toString() {
        return "ArticleVo{" +
                "articleId=" + articleId +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", categoryName='" + categoryName + '\'' +
                ", tags='" + tags + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleDesc='" + articleDesc + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", publicStatus=" + publicStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
