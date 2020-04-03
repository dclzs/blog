package plus.kuailefeizhaijidi.blog.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author dl
 * @since 2020年4月3日
 */
public class CategoryDto {

    @Length(min = 1,max = 100)
    @NotNull
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    @ApiModelProperty(value = "分类描述")
    private String categoryDesc;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                '}';
    }
}
