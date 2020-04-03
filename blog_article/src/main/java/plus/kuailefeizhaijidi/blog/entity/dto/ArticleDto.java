package plus.kuailefeizhaijidi.blog.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author dl
 * @since 2020年4月3日
 */
public class ArticleDto {

    @NotNull
    @ApiModelProperty(value = "分类ID")
    private Long categoryId;
    @ApiModelProperty(value = "标签（逗号分隔）")
    private String tags;
    @Length(min = 1,max = 100)
    @NotNull
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    @ApiModelProperty(value = "原作者")
    private String articleAuthor;
    @ApiModelProperty(value = "文章描述")
    private String articleDesc;
    @ApiModelProperty(value = "文章内容")
    private String articleContent;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "ArticleDto{" +
                "categoryId=" + categoryId +
                ", tags='" + tags + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleDesc='" + articleDesc + '\'' +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }
}
